package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.*;
import com.lucalucenak.Noxus.dtos.post.OrderPostDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDrinkFieldDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnProductFieldDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnSoupFieldDto;
import com.lucalucenak.Noxus.exceptions.*;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import com.lucalucenak.Noxus.models.pks.OrderProductPk;
import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import com.lucalucenak.Noxus.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

                OrderReturnProductFieldDto product = new OrderReturnProductFieldDto(
                        new ProductModel(productFullDto),
                        productQuantity,
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

        for (Map.Entry<Long, Integer> i : orderPostDto.getProductsIds().entrySet()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getKey()));
            Integer productQuantity = i.getValue();

            orderPrice += productModel.getPrice() * productQuantity;
        }

        orderModel.setOrderPrice(orderPrice);

        //Saving Order
        orderRepository.save(orderModel);

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);

        List<OrderReturnProductFieldDto> products = new ArrayList<>();
        for (Map.Entry<Long, Integer> i : orderPostDto.getProductsIds().entrySet()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getKey()));
            Integer productQuantity = i.getValue();

            OrderProductPk orderProductPk = new OrderProductPk(orderModel, productModel);

            OrderProductFullDto orderProductFullDto = orderProductService.saveOrderProduct(new OrderProductFullDto(orderProductPk, productQuantity));

            OrderReturnProductFieldDto product = new OrderReturnProductFieldDto(
                    productModel,
                    productQuantity,
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

        for (Map.Entry<Long, Integer> i : orderPostDto.getProductsIds().entrySet()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getKey()));
            Integer productQuantity = i.getValue();

            orderPrice += productModel.getPrice() * productQuantity;
        }

        updatedOrderModel.setOrderPrice(orderPrice);

        //Saving Order
        BeanUtils.copyProperties(updatedOrderModel, existentOrderModel, "createdAt, updatedAt");
        orderRepository.save(existentOrderModel);

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(existentOrderModel);

        List<OrderReturnProductFieldDto> products = new ArrayList<>();
        for (Map.Entry<Long, Integer> i : orderPostDto.getProductsIds().entrySet()) {
            ProductModel productModel = new ProductModel(productService.findProductById(i.getKey()));
            Integer productQuantity = i.getValue();

            OrderProductPk orderProductPk = new OrderProductPk(existentOrderModel, productModel);

            OrderProductFullDto orderProductFullDto = orderProductService.saveOrderProduct(new OrderProductFullDto(orderProductPk, productQuantity));

            OrderReturnProductFieldDto product = new OrderReturnProductFieldDto(
                    productModel,
                    productQuantity,
                    productModel.getPrice() * productQuantity
            );
            products.add(product);
            orderReturnDto.setProducts(products);
        }

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
    public OrderReturnDto finishOrderById(Long orderId) {
        OrderModel orderModel = new OrderModel(this.findOrderById(orderId));
        orderModel.setStatus(new StatusModel(statusService.findStatusByStatus("FINISHED")));
        orderRepository.save(orderModel);
        clientAccountService.increasePlacedOrdersQuantityByClientAccountId(orderModel.getClientAccount().getId());

        List<OrderProductFullDto> orderProducts = orderProductService.findOrderProductsByOrderId(orderId);
        List<OrderReturnProductFieldDto> products = new ArrayList<>();
        for (OrderProductFullDto i : orderProducts) {
            ProductModel productModel = i.getId().getProduct();
            OrderReturnProductFieldDto orderReturnDrinkFieldDto = new OrderReturnProductFieldDto(
                    productModel,
                    i.getQuantity(),
                    productModel.getPrice() * i.getQuantity()
            );
            products.add(orderReturnDrinkFieldDto);
        }

        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);
        orderReturnDto.setProducts(products);

        return orderReturnDto;
    }
}
