package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.*;
import com.lucalucenak.Noxus.dtos.post.OrderPostDto;
import com.lucalucenak.Noxus.dtos.response.OrderReturnDto;
import com.lucalucenak.Noxus.enums.DeliveryTypeEnum;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import com.lucalucenak.Noxus.repositories.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Transactional
    public OrderFullDto findOrderById(Long orderId) {
        Optional<OrderModel> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isPresent()) {
            return new OrderFullDto(orderOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: Order. Not found with id: " + orderId);
        }
    }

    @Transactional
    public Page<OrderFullDto> findAllOrdersPaginated(Pageable pageable) {
        Page<OrderModel> pagedOrders = orderRepository.findAll(pageable);
        return pagedOrders.map(OrderFullDto::new);
    }

    @Transactional
    public OrderReturnDto saveOrder(OrderPostDto orderPostDto) {
        OrderModel orderModel = new OrderModel(orderPostDto);
        Double orderPrice = 0.0;

        AddressModel addressModel = new AddressModel(addressService.findAddressById(orderPostDto.getAddressId()));
        orderModel.setAddress(addressModel);
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(orderPostDto.getClientAccountId()));
        orderModel.setClientAccount(clientAccountModel);
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel(paymentMethodService.findPaymentMethodById(orderPostDto.getPaymentMethodId()));
        orderModel.setPaymentMethod(paymentMethodModel);
        DeliveryTypeModel deliveryTypeModel = new DeliveryTypeModel(deliveryTypeService.findDeliveryTypeById(orderPostDto.getDeliveryTypeId()));
        orderModel.setDeliveryType(deliveryTypeModel);
        StatusModel statusModel = new StatusModel(statusService.findStatusByStatus("ORDERED"));
        orderModel.setStatus(statusModel);

        // Setting Order Price
        if (deliveryTypeModel.getDeliveryType().equals(DeliveryTypeEnum.DELIVERY)) {
            orderPrice += addressModel.getNeighbourhood().getDeliveryTax();
        }

        for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
            SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
            Integer soupQuantity = i.getValue();

            orderPrice += soupModel.getPrice() * soupQuantity;
        }

        for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
            DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
            Integer drinkQuantity = i.getValue();

            orderPrice += drinkModel.getPrice() * drinkQuantity;
        }
        orderModel.setOrderPrice(orderPrice);

        //Saving Order
        orderRepository.save(orderModel);

        // Saving Order Soup
        Map<SoupFullDto, Integer> soups = new HashMap<>();
        for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
            SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
            Integer soupQuantity = i.getValue();

            OrderSoupPk orderSoupPk = new OrderSoupPk(orderModel, soupModel);

            OrderSoupFullDto orderSoupFullDto = orderSoupService.saveOrderSoup(new OrderSoupFullDto(orderSoupPk, soupQuantity));
            soups.put(new SoupFullDto(soupModel), soupQuantity);
        }

        // Saving Order Drink
        Map<DrinkFullDto, Integer> drinks = new HashMap<>();
        for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
            DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
            Integer drinkQuantity = i.getValue();

            OrderDrinkPk orderDrinkPk = new OrderDrinkPk(orderModel, drinkModel);

            OrderDrinkFullDto orderDrinkFullDto = orderDrinkService.saveOrderDrink(new OrderDrinkFullDto(orderDrinkPk, drinkQuantity));
            drinks.put(new DrinkFullDto(drinkModel), drinkQuantity);
        }

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(orderModel);
        orderReturnDto.setSoups(soups);
        orderReturnDto.setDrinks(drinks);

        return orderReturnDto;
    }

    @Transactional
    public OrderReturnDto updateOrder(Long orderId, OrderPostDto orderPostDto) {
        OrderModel existentOrderModel = new OrderModel(this.findOrderById(orderId));
        OrderModel updatedOrderModel = new OrderModel(orderPostDto);
        OrderModel orderModel = new OrderModel(orderPostDto);
        Double orderPrice = 0.0;

        AddressModel addressModel = new AddressModel(addressService.findAddressById(orderPostDto.getAddressId()));
        updatedOrderModel.setAddress(addressModel);
        ClientAccountModel clientAccountModel = new ClientAccountModel(clientAccountService.findClientAccountById(orderPostDto.getClientAccountId()));
        updatedOrderModel.setClientAccount(clientAccountModel);
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel(paymentMethodService.findPaymentMethodById(orderPostDto.getPaymentMethodId()));
        updatedOrderModel.setPaymentMethod(paymentMethodModel);
        DeliveryTypeModel deliveryTypeModel = new DeliveryTypeModel(deliveryTypeService.findDeliveryTypeById(orderPostDto.getDeliveryTypeId()));
        updatedOrderModel.setDeliveryType(deliveryTypeModel);

        // Setting Order Price
        if (deliveryTypeModel.getDeliveryType().equals(DeliveryTypeEnum.DELIVERY)) {
            orderPrice += addressModel.getNeighbourhood().getDeliveryTax();
        }

        for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
            SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
            Integer soupQuantity = i.getValue();

            orderPrice += soupModel.getPrice() * soupQuantity;
        }

        for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
            DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
            Integer drinkQuantity = i.getValue();

            orderPrice += drinkModel.getPrice() * drinkQuantity;
        }
        orderModel.setOrderPrice(orderPrice);

        //Saving Order
        BeanUtils.copyProperties(updatedOrderModel, existentOrderModel, "id");
        orderRepository.save(existentOrderModel);

        // Saving Order Soup
        Map<SoupFullDto, Integer> soups = new HashMap<>();
        for (Map.Entry<Long, Integer> i : orderPostDto.getSoupsIds().entrySet()) {
            SoupModel soupModel = new SoupModel(soupService.findSoupById(i.getKey()));
            Integer soupQuantity = i.getValue();

            OrderSoupPk orderSoupPk = new OrderSoupPk(orderModel, soupModel);

            OrderSoupFullDto orderSoupFullDto = orderSoupService.saveOrderSoup(new OrderSoupFullDto(orderSoupPk, soupQuantity));
            soups.put(new SoupFullDto(soupModel), soupQuantity);
        }

        // Saving Order Drink
        Map<DrinkFullDto, Integer> drinks = new HashMap<>();
        for (Map.Entry<Long, Integer> i : orderPostDto.getDrinksIds().entrySet()) {
            DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(i.getKey()));
            Integer drinkQuantity = i.getValue();

            OrderDrinkPk orderDrinkPk = new OrderDrinkPk(orderModel, drinkModel);

            OrderDrinkFullDto orderDrinkFullDto = orderDrinkService.saveOrderDrink(new OrderDrinkFullDto(orderDrinkPk, drinkQuantity));
            drinks.put(new DrinkFullDto(drinkModel), drinkQuantity);
        }

        // Setting Return
        OrderReturnDto orderReturnDto = new OrderReturnDto(existentOrderModel);
        orderReturnDto.setSoups(soups);
        orderReturnDto.setDrinks(drinks);

        return orderReturnDto;
    }
}
