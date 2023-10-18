package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.*;
import com.lucalucenak.Noxus.dtos.post.OrderPostDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDrinkFieldDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnSoupFieldDto;
import com.lucalucenak.Noxus.exceptions.*;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
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
    private SoupService soupService;
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private OrderSoupService orderSoupService;
    @Autowired
    private OrderDrinkService orderDrinkService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private DeliveryService deliveryService;

    @Transactional
    public OrderReturnDto findOrderById(Long orderId) {
        Optional<OrderModel> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            OrderReturnDto orderReturnDto = new OrderReturnDto(orderOptional.get());

            List<OrderReturnSoupFieldDto> soups = new ArrayList<>();
            for (OrderSoupFullDto i : orderSoupService.findOrderSoupsByOrderId(orderId)) {
                SoupFullDto soupFullDto = soupService.findSoupById(i.getId().getSoup().getId());
                Integer quantity = i.getQuantity();

                OrderReturnSoupFieldDto soup = new OrderReturnSoupFieldDto(
                        new SoupModel(soupFullDto),
                        quantity,
                        soupFullDto.getPrice() * quantity
                );
                soups.add(soup);
            }
            orderReturnDto.setSoups(soups);

            List<OrderReturnDrinkFieldDto> drinks = new ArrayList<>();
            for (OrderDrinkFullDto i : orderDrinkService.findOrderDrinksByOrderId(orderId)) {
                DrinkFullDto drinkFullDto = drinkService.findDrinkById(i.getId().getDrink().getId());
                Integer quantity = i.getQuantity();

                OrderReturnDrinkFieldDto drink = new OrderReturnDrinkFieldDto(
                        new DrinkModel(drinkFullDto),
                        quantity,
                        drinkFullDto.getPrice() * quantity
                );
                drinks.add(drink);
            }
            orderReturnDto.setDrinks(drinks);

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

            List<OrderReturnSoupFieldDto> soups = new ArrayList<>();
            for (OrderSoupFullDto j : orderSoupService.findOrderSoupsByOrderId(i.getId())) {
                SoupFullDto soupFullDto = soupService.findSoupById(j.getId().getSoup().getId());
                Integer quantity = j.getQuantity();
                OrderReturnSoupFieldDto soup = new OrderReturnSoupFieldDto(
                        new SoupModel(soupFullDto),
                        quantity,
                        soupFullDto.getPrice() * quantity
                );
                soups.add(soup);
            }
            orderReturnDto.setSoups(soups);

            List<OrderReturnDrinkFieldDto> drinks = new ArrayList<>();
            for (OrderDrinkFullDto j : orderDrinkService.findOrderDrinksByOrderId(i.getId())) {
                DrinkFullDto drinkFullDto = drinkService.findDrinkById(j.getId().getDrink().getId());
                Integer quantity = j.getQuantity();

                OrderReturnDrinkFieldDto drink = new OrderReturnDrinkFieldDto(
                        new DrinkModel(drinkFullDto),
                        quantity,
                        drinkFullDto.getPrice() * quantity
                );
                drinks.add(drink);
            }
            orderReturnDto.setDrinks(drinks);

            orderReturnDtos.add(orderReturnDto);
        }

        Page<OrderReturnDto> orderReturnDtoPage = new PageImpl<>(orderReturnDtos, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), orderReturnDtos.size());
        return orderReturnDtoPage;
    }


    @Transactional
    public OrderReturnDto saveOrder(OrderPostDto orderPostDto) {
        if (orderPostDto.getSoupsIds() == null && orderPostDto.getDrinksIds() == null) {
            throw new MustOrderAtLeastOneSoupOrOneDrinkException("You must to order at least one soup or one drink.");
        }
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

        if (orderPostDto.getSoupsIds() != null) {
            for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
                SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
                Integer soupQuantity = i.getValue();

                orderPrice += soupModel.getPrice() * soupQuantity;
            }
        }

        if (orderPostDto.getDrinksIds() != null) {
            for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
                DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
                Integer drinkQuantity = i.getValue();

                orderPrice += drinkModel.getPrice() * drinkQuantity;
            }
        }

        orderModel.setOrderPrice(orderPrice);

        //Saving Order
        orderRepository.save(orderModel);

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);

        // Saving Order Soup
        if (orderPostDto.getSoupsIds() != null) {
            List<OrderReturnSoupFieldDto> soups = new ArrayList<>();
            for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
                SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
                Integer soupQuantity = i.getValue();

                OrderSoupPk orderSoupPk = new OrderSoupPk(orderModel, soupModel);

                OrderSoupFullDto orderSoupFullDto = orderSoupService.saveOrderSoup(new OrderSoupFullDto(orderSoupPk, soupQuantity));

                OrderReturnSoupFieldDto soup = new OrderReturnSoupFieldDto(
                        soupModel,
                        soupQuantity,
                        soupModel.getPrice() * soupQuantity
                );
                soups.add(soup);
            }
            orderReturnDto.setSoups(soups);
        }

        // Saving Order Drink
        if (orderPostDto.getDrinksIds() != null) {
            List<OrderReturnDrinkFieldDto> drinks = new ArrayList<>();
            for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
                DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
                Integer drinkQuantity = i.getValue();

                OrderDrinkPk orderDrinkPk = new OrderDrinkPk(orderModel, drinkModel);

                OrderDrinkFullDto orderDrinkFullDto = orderDrinkService.saveOrderDrink(new OrderDrinkFullDto(orderDrinkPk, drinkQuantity));

                OrderReturnDrinkFieldDto drink = new OrderReturnDrinkFieldDto(
                        drinkModel,
                        drinkQuantity,
                        drinkModel.getPrice() * drinkQuantity
                );
                drinks.add(drink);
            }
            orderReturnDto.setDrinks(drinks);
        }
        return orderReturnDto;
    }

    @Transactional
    public OrderReturnDto updateOrder(Long orderId, OrderPostDto orderPostDto) {
        if (!orderId.equals(orderPostDto.getId())) {
            throw new IncompatibleIdsException("Path param Id and body Id must be equals. Path Param Id: " + orderId + ", Body Id: " + orderPostDto.getId());
        }
        if (orderPostDto.getSoupsIds() == null && orderPostDto.getDrinksIds() == null) {
            throw new MustOrderAtLeastOneSoupOrOneDrinkException("You must to order at least one soup or one drink.");
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

        if (orderPostDto.getSoupsIds() != null) {
            for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
                SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
                Integer soupQuantity = i.getValue();

                orderPrice += soupModel.getPrice() * soupQuantity;
            }
        }

        if (orderPostDto.getDrinksIds() != null) {
            for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
                DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
                Integer drinkQuantity = i.getValue();

                orderPrice += drinkModel.getPrice() * drinkQuantity;
            }
        }

        updatedOrderModel.setOrderPrice(orderPrice);

        //Saving Order
        BeanUtils.copyProperties(updatedOrderModel, existentOrderModel, "createdAt, updatedAt");
        orderRepository.save(existentOrderModel);

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(existentOrderModel);

        // Saving Order Soup
        if (orderPostDto.getSoupsIds() != null) {
            orderSoupService.deleteOrderSoupByOrderId(orderId); // Delete existent relationships and recreate
            List<OrderReturnSoupFieldDto> soups = new ArrayList<>();
            for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
                SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
                Integer soupQuantity = i.getValue();

                OrderSoupPk orderSoupPk = new OrderSoupPk(existentOrderModel, soupModel);

                OrderSoupFullDto orderSoupFullDto = orderSoupService.saveOrderSoup(new OrderSoupFullDto(orderSoupPk, soupQuantity));
                OrderReturnSoupFieldDto soup = new OrderReturnSoupFieldDto(
                        soupModel,
                        soupQuantity,
                        soupModel.getPrice() * soupQuantity
                );
                soups.add(soup);
            }
            orderReturnDto.setSoups(soups);
        }

        // Saving Order Drink
        if (orderPostDto.getDrinksIds() != null) {
            orderDrinkService.deleteOrderDrinkByOrderId(orderId); // Delete existent relationships and recreate
            List<OrderReturnDrinkFieldDto> drinks = new ArrayList<>();
            for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
                DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
                Integer drinkQuantity = i.getValue();

                OrderDrinkPk orderDrinkPk = new OrderDrinkPk(existentOrderModel, drinkModel);

                OrderDrinkFullDto orderDrinkFullDto = orderDrinkService.saveOrderDrink(new OrderDrinkFullDto(orderDrinkPk, drinkQuantity));

                OrderReturnDrinkFieldDto drink = new OrderReturnDrinkFieldDto(
                        drinkModel,
                        drinkQuantity,
                        drinkModel.getPrice() * drinkQuantity
                );
                drinks.add(drink);
            }
            orderReturnDto.setDrinks(drinks);
        }

        return orderReturnDto;
    }

    @Transactional
    public void deleteOrderById(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderSoupService.deleteOrderSoupByOrderId(orderId);
            orderDrinkService.deleteOrderDrinkByOrderId(orderId);
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

        List<OrderDrinkFullDto> orderDrinks = orderDrinkService.findOrderDrinksByOrderId(orderId);
        List<OrderReturnDrinkFieldDto> drinks = new ArrayList<>();
        for (OrderDrinkFullDto i : orderDrinks) {
            DrinkModel drinkModel = i.getId().getDrink();
            OrderReturnDrinkFieldDto orderReturnDrinkFieldDto = new OrderReturnDrinkFieldDto(
                    drinkModel,
                    i.getQuantity(),
                    drinkModel.getPrice() * i.getQuantity()
            );
            drinks.add(orderReturnDrinkFieldDto);
        }

        List<OrderSoupFullDto> orderSoups = orderSoupService.findOrderSoupsByOrderId(orderId);
        List<OrderReturnSoupFieldDto> soups = new ArrayList<>();
        for (OrderSoupFullDto i : orderSoups) {
            SoupModel soupModel = i.getId().getSoup();
            OrderReturnSoupFieldDto orderReturnSoupFieldDto = new OrderReturnSoupFieldDto(
                    soupModel,
                    i.getQuantity(),
                    soupModel.getPrice() * i.getQuantity()
            );
            soups.add(orderReturnSoupFieldDto);
        }

        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);
        orderReturnDto.setDrinks(drinks);
        orderReturnDto.setSoups(soups);

        return orderReturnDto;
    }
}
