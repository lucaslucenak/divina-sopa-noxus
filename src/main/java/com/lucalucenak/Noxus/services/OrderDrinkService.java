package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.OrderDrinkFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.OrderDrinkModel;
import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import com.lucalucenak.Noxus.repositories.OrderDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderDrinkService {

    @Autowired
    private OrderDrinkRepository orderDrinkRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DrinkService drinkService;

    @Transactional
    public OrderDrinkFullDto findOrderDrinkById(Long orderId, Long drinkId) {
        OrderModel orderModel = new OrderModel(orderService.findOrderById(orderId));
        DrinkModel drinkModel = new DrinkModel(drinkService.findDrinkById(drinkId));
        OrderDrinkPk orderDrinkPk = new OrderDrinkPk(orderModel, drinkModel);

        Optional<OrderDrinkModel> orderDrinkOptional = orderDrinkRepository.findById(orderDrinkPk);

        if (orderDrinkOptional.isPresent()) {
            return new OrderDrinkFullDto(orderDrinkOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: OrderDrink. Not found with order id: "+ orderId + " and drink id: " + drinkId);
        }
    }

    @Transactional
    public Page<OrderDrinkFullDto> findAllOrderDrinksPaginated(Pageable pageable) {
        Page<OrderDrinkModel> pagedOrderDrinks = orderDrinkRepository.findAll(pageable);
        return pagedOrderDrinks.map(OrderDrinkFullDto::new);
    }

    @Transactional
    public OrderDrinkFullDto saveOrderDrink(OrderDrinkFullDto orderDrinkFullDto) {
        OrderDrinkModel orderDrinkModel = new OrderDrinkModel(orderDrinkFullDto);
        return new OrderDrinkFullDto(orderDrinkRepository.save(orderDrinkModel));
    }
}
