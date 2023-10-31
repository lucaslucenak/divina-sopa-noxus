package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.*;
import com.lucalucenak.Noxus.dtos.post.OrderPostDto;
import com.lucalucenak.Noxus.dtos.post.OrderProductPostDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnProductFieldDto;
import com.lucalucenak.Noxus.enums.PaymentMethodEnum;
import com.lucalucenak.Noxus.exceptions.*;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.models.pks.OrderProductPk;
import com.lucalucenak.Noxus.repositories.CashRegisterBalanceRepository;
import com.lucalucenak.Noxus.repositories.OrderRepository;
import com.lucalucenak.Noxus.utils.LocalDateTimeUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ClientAccountService clientAccountService;
    @Autowired
    private PaymentMethodService paymentMethodService;
    @Autowired
    private DeliveryTypeService deliveryTypeService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderProductService orderProductService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private LocalDateTimeUtil localDateTimeUtil;
    @Autowired
    private CashRegisterBalanceRepository cashRegisterBalanceRepository;

    @Transactional
    public OrderReturnDto findOrderById(Long orderId) {
        Optional<OrderModel> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            OrderReturnDto orderReturnDto = new OrderReturnDto(orderOptional.get());

            List<OrderReturnProductFieldDto> products = new ArrayList<>();
            for (OrderProductFullDto i : orderProductService.findOrderProductsByOrderId(orderId)) {
                ProductFullDto productFullDto = productService.findProductById(i.getId().getProduct().getId());
                Integer productQuantity = i.getQuantity();

                OrderReturnProductFieldDto product = new OrderReturnProductFieldDto(
                        new ProductModel(productFullDto),
                        productQuantity,
                        i.getAdditions(),
                        productFullDto.getPrice() * productQuantity
                );
                products.add(product);
            }
            orderReturnDto.setProducts(products);

            return orderReturnDto;
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + orderId);
        }
    }

    @Transactional
    public Page<OrderReturnDto> findAllOrdersPaginated(Pageable pageable) {
        Page<OrderModel> pagedOrders = orderRepository.findAll(pageable);

        List<OrderReturnDto> orderReturnDtos = new ArrayList<>();
        for (OrderModel i : pagedOrders) {
            OrderReturnDto orderReturnDto = new OrderReturnDto(i);

            List<OrderReturnProductFieldDto> products = new ArrayList<>();
            for (OrderProductFullDto j : orderProductService.findOrderProductsByOrderId(i.getId())) {
                ProductFullDto productFullDto = productService.findProductById(j.getId().getProduct().getId());
                Integer productQuantity = j.getQuantity();
                String productAdditions = j.getAdditions();

                OrderReturnProductFieldDto product = new OrderReturnProductFieldDto(
                        new ProductModel(productFullDto),
                        productQuantity,
                        productAdditions,
                        productFullDto.getPrice() * productQuantity
                );
                products.add(product);
            }
            orderReturnDto.setProducts(products);

            orderReturnDtos.add(orderReturnDto);
        }

        Page<OrderReturnDto> orderReturnDtoPage = new PageImpl<>(orderReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), orderReturnDtos.size());
        return orderReturnDtoPage;
    }

    @Transactional
    public OrderReturnDto saveOrder(OrderPostDto orderPostDto) {

        if (orderRepository.existsByDeliveryId(orderPostDto.getDeliveryId())) {
            OrderModel existentOrderModel = orderRepository.findByDeliveryId(orderPostDto.getDeliveryId()).get();
            throw new DeliveryAlreadyUsedAtAnotherOrderException("The given delivery id: " + orderPostDto.getDeliveryId() + " is already used at Order with id: " + existentOrderModel.getId());
        }

        OrderModel orderModel = new OrderModel(orderPostDto);
        Double orderPrice = 0.0;

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        CashRegisterBalanceModel cashRegisterBalance = cashRegisterBalanceRepository.findTopByCreatedAtBetweenOrderByCreatedAtDesc(startOfDay, endOfDay)
                .orElseThrow(() -> new ResourceNotFoundException("CashRegisterBalance not found for current day."));

        orderModel.setCashRegisterBalance(cashRegisterBalance);

        DeliveryModel deliveryModel = new DeliveryModel(deliveryService.findDeliveryById(orderPostDto.getDeliveryId()));
        orderModel.setDelivery(deliveryModel);
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(orderPostDto.getClientAccountId()));
        orderModel.setClientAccount(clientAccountModel);
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel(paymentMethodService.findPaymentMethodById(orderPostDto.getPaymentMethodId()));
        orderModel.setPaymentMethod(paymentMethodModel);
        StatusModel statusModel = new StatusModel(statusService.findStatusByStatus("ORDERED"));
        orderModel.setStatus(statusModel);

        if (!addressService.belongsToClientAccount(clientAccountModel.getId())) {
            throw new AddressNotBelongingToClientAccountException("The given address doesn't belongs to the given client account. Client Account id: " + clientAccountModel.getId() + " | Address id: " + deliveryModel.getAddress().getId());
        }

        // Setting Order Price
        if (deliveryModel.getDeliveryType().getDeliveryType().equals("DELIVERY")) {
            orderPrice += deliveryModel.getTax();
        }

        for (OrderProductPostDto i : orderPostDto.getProducts()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getProductId()));
            Integer productQuantity = i.getQuantity();

            orderPrice += productModel.getPrice() * productQuantity;
        }

        // Discounting Coupon, if available
        if (orderPostDto.getCouponId() != null) {
            CouponModel couponModel = new CouponModel(couponService.findCouponById(orderPostDto.getCouponId()));
            orderModel.setCoupon(couponModel);
            if (this.countByClientAccountIdAndCouponId(clientAccountModel.getId(), couponModel.getId()) >= couponModel.getMaxUsages()) {
                throw new CouponMaxUsageReachedException("Coupon Max Usage Reached for Client Account with id: " + clientAccountModel.getId());
            }
            if (couponModel.getStatus().getStatus().equals("EXPIRED") || couponModel.getStatus().getStatus().equals("INACTIVE")) {
                throw new CouponNotAvailableException("Coupon not available");
            }

            if (orderPrice - deliveryModel.getTax() >= couponModel.getMinimumOrderValue()) {
                orderModel.setCoupon(couponModel);
                orderPrice -= couponModel.getCouponValue();
            } else {
                throw new OrderValueLowerThanCouponMinimumValueException("Can not use Coupon. Order value is lower than coupon minimum value. Minimum value: " + couponModel.getCouponValue() + " | Order Values: " + orderPrice);
            }
        }

        //Setting Paid Value
        if (orderModel.getPaymentMethod().getPaymentMethod().equals(PaymentMethodEnum.CASH)) {

            if (orderModel.getPaidValue() == null)
                throw new PaymentMethodCashWithoutPaidValueException("If the payment is in cash, it is necessary to add the paid value.");

            if (orderModel.getPaidValue() < orderPrice)
                throw new PaidValueLessThanOrderPriceException("The paid value cannot be less than order price.");

            var change = orderModel.getPaidValue() - orderPrice;
            orderModel.setChange(change);
        }
        else {
            orderModel.setPaidValue(orderPrice);
        }

        orderModel.setOrderPrice(orderPrice);

        //Saving Order
        orderRepository.save(orderModel);

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);

        List<OrderReturnProductFieldDto> products = new ArrayList<>();
        for (OrderProductPostDto i : orderPostDto.getProducts()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getProductId()));
            Integer productQuantity = i.getQuantity();
            String productAdditions = i.getAdditions();

            OrderProductPk orderProductPk = new OrderProductPk(orderModel, productModel);

            OrderProductFullDto orderProductFullDto = orderProductService.saveOrderProduct(new OrderProductFullDto(orderProductPk, productAdditions, productQuantity));

            OrderReturnProductFieldDto product = new OrderReturnProductFieldDto(
                    productModel,
                    productQuantity,
                    productAdditions,
                    productModel.getPrice() * productQuantity
            );
            products.add(product);
        }

        orderReturnDto.setProducts(products);

        return orderReturnDto;
    }

    @Transactional
    public OrderReturnDto updateOrder(Long orderId, OrderPostDto orderPostDto) {
        if (!orderId.equals(orderPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + orderId + ", Body Id: " + orderPostDto.getId());
        }

        OrderModel existentOrderModel = new OrderModel(this.findOrderById(orderId));
        OrderModel updatedOrderModel = new OrderModel(orderPostDto);
        Double orderPrice = 0.0;

        DeliveryModel deliveryModel = new DeliveryModel(deliveryService.findDeliveryById(orderPostDto.getDeliveryId()));
        updatedOrderModel.setDelivery(deliveryModel);
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(orderPostDto.getClientAccountId()));
        updatedOrderModel.setClientAccount(clientAccountModel);
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel(paymentMethodService.findPaymentMethodById(orderPostDto.getPaymentMethodId()));
        updatedOrderModel.setPaymentMethod(paymentMethodModel);
        StatusModel statusModel = new StatusModel(statusService.findStatusByStatus("ORDERED"));
        updatedOrderModel.setStatus(statusModel);

        // Setting Order Price
        if (deliveryModel.getDeliveryType().getDeliveryType().equals("DELIVERY")) {
            orderPrice += deliveryModel.getTax();
        }

        for (OrderProductPostDto i : orderPostDto.getProducts()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getProductId()));
            Integer productQuantity = i.getQuantity();

            orderPrice += productModel.getPrice() * productQuantity;
        }

        // Discounting Coupon, if available
        if (orderPostDto.getCouponId() != null) {
            CouponModel couponModel = new CouponModel(couponService.findCouponById(orderPostDto.getCouponId()));
            updatedOrderModel.setCoupon(couponModel);
            if (this.countByClientAccountIdAndCouponId(clientAccountModel.getId(), couponModel.getId()) >= couponModel.getMaxUsages()) {
                throw new CouponMaxUsageReachedException("Coupon Max Usage Reached for Client Account with id: " + clientAccountModel.getId());
            }
            if (couponModel.getStatus().getStatus().equals("EXPIRED") || couponModel.getStatus().getStatus().equals("INACTIVE")) {
                throw new CouponNotAvailableException("Coupon not available");
            }

            if (orderPrice - deliveryModel.getTax() >= couponModel.getMinimumOrderValue()) {
                updatedOrderModel.setCoupon(couponModel);
                orderPrice -= couponModel.getCouponValue();
            } else {
                throw new OrderValueLowerThanCouponMinimumValueException("Can not use Coupon. Order value is lower than coupon minimum value. Minimum value: " + couponModel.getCouponValue() + " | Order Values: " + orderPrice);
            }
        }

        //Setting Paid Value
        if (updatedOrderModel.getPaymentMethod().getPaymentMethod().equals(PaymentMethodEnum.CASH)) {

            if (updatedOrderModel.getPaidValue() == null)
                throw new PaymentMethodCashWithoutPaidValueException("If the payment is in cash, it is necessary to add the paid value.");

            if (updatedOrderModel.getPaidValue() < orderPrice)
                throw new PaidValueLessThanOrderPriceException("The paid value cannot be less than order price.");

            var change = updatedOrderModel.getPaidValue() - orderPrice;
            updatedOrderModel.setChange(change);
        }
        else {
            updatedOrderModel.setPaidValue(orderPrice);
        }

        updatedOrderModel.setOrderPrice(orderPrice);

        //Saving Order
        updatedOrderModel.setCreatedAt(existentOrderModel.getCreatedAt());
        BeanUtils.copyProperties(updatedOrderModel, existentOrderModel, "createdAt, updatedAt");
        orderRepository.save(existentOrderModel);

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(existentOrderModel);

        List<OrderReturnProductFieldDto> products = new ArrayList<>();
        for (OrderProductPostDto i : orderPostDto.getProducts()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getProductId()));
            Integer productQuantity = i.getQuantity();
            String productAdditions = i.getAdditions();

            OrderProductPk orderProductPk = new OrderProductPk(existentOrderModel, productModel);

            OrderProductFullDto orderProductFullDto = orderProductService.saveOrderProduct(new OrderProductFullDto(orderProductPk, productAdditions, productQuantity));

            OrderReturnProductFieldDto product = new OrderReturnProductFieldDto(
                    productModel,
                    productQuantity,
                    productAdditions,
                    productModel.getPrice() * productQuantity
            );
            products.add(product);
        }
        orderReturnDto.setProducts(products);

        return orderReturnDto;
    }

    @Transactional
    public void deleteOrderById(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderProductService.deleteOrderProductByOrderId(orderId);
            orderRepository.deleteById(orderId);
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + orderId);
        }
    }

    @Transactional
    public Long countByClientAccountIdAndCouponId(Long clientAccountId, Long couponId) {
        return orderRepository.countByClientAccountIdAndCouponId(clientAccountId, couponId);
    }

    @Transactional
    public OrderReturnDto dispatchOrderById(Long orderId) {
        OrderModel orderModel = new OrderModel(this.findOrderById(orderId));
        orderModel.setStatus(new StatusModel(statusService.findStatusByStatus("DISPATCHED")));
        orderModel.setDispatchTime(localDateTimeUtil.nowGMT3());
        orderRepository.save(orderModel);

        List<OrderProductFullDto> orderProducts = orderProductService.findOrderProductsByOrderId(orderId);
        List<OrderReturnProductFieldDto> products = new ArrayList<>();
        for (OrderProductFullDto i : orderProducts) {
            ProductModel productModel = i.getId().getProduct();
            OrderReturnProductFieldDto orderReturnDrinkFieldDto = new OrderReturnProductFieldDto(
                    productModel,
                    i.getQuantity(),
                    i.getAdditions(),
                    productModel.getPrice() * i.getQuantity()
            );
            products.add(orderReturnDrinkFieldDto);
        }

        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);
        orderReturnDto.setProducts(products);

        return orderReturnDto;

    }

    @Transactional
    public OrderReturnDto finishOrderById(Long orderId) {
        OrderModel orderModel = new OrderModel(this.findOrderById(orderId));
        orderModel.setStatus(new StatusModel(statusService.findStatusByStatus("FINISHED")));
        orderRepository.save(orderModel);
        System.out.println(orderModel.getCashRegisterBalance().getId());
        clientAccountService.increasePlacedOrdersQuantityByClientAccountId(orderModel.getClientAccount().getId());

        List<OrderProductFullDto> orderProducts = orderProductService.findOrderProductsByOrderId(orderId);
        List<OrderReturnProductFieldDto> products = new ArrayList<>();
        for (OrderProductFullDto i : orderProducts) {
            ProductModel productModel = i.getId().getProduct();
            OrderReturnProductFieldDto orderReturnDrinkFieldDto = new OrderReturnProductFieldDto(
                    productModel,
                    i.getQuantity(),
                    i.getAdditions(),
                    productModel.getPrice() * i.getQuantity()
            );
            products.add(orderReturnDrinkFieldDto);
        }

        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);
        orderReturnDto.setProducts(products);

        return orderReturnDto;
    }
}
