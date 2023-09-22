package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.OrderDrinkFullDto;
import com.lucalucenak.Noxus.dtos.OrderSoupFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.OrderDrinkModel;
import com.lucalucenak.Noxus.models.DrinkModel;
import com.lucalucenak.Noxus.models.OrderSoupModel;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import com.lucalucenak.Noxus.repositories.OrderDrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    public OrderDrinkFullDto findOrderDrinkById(OrderDrinkPk orderDrinkPk) {
        Optional<OrderDrinkModel> orderDrinkOptional = orderDrinkRepository.findById(orderDrinkPk);

        if (orderDrinkOptional.isPresent()) {
            return new OrderDrinkFullDto(orderDrinkOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: OrderDrink. Not found with order id: "+ orderDrinkPk.getOrder().getId() + " and drink id: " + orderDrinkPk.getDrink().getId());
        }
    }


    @Transactional
    public List<OrderDrinkFullDto> findOrderDrinksByOrderId(Long orderId) {
        List<Optional<OrderDrinkModel>> orderDrinksOptional = orderDrinkRepository.findByIdOrderId(orderId);

        if (orderDrinksOptional.size() < 1) {
            throw new ResourceNotFoundException("Resource: OrderDrink. Not found with order id: " + orderId);
        } else {
            List<OrderDrinkFullDto> orderDrinkFullDtos = new ArrayList<>();
            for (Optional<OrderDrinkModel> i : orderDrinksOptional) {
                orderDrinkFullDtos.add(new OrderDrinkFullDto(i.get()));
            }
            return orderDrinkFullDtos;
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

    @Transactional
    public void deleteOrderDrinkByOrderId(Long orderId) {
        if (orderDrinkRepository.existsByIdOrderId(orderId)) {
            orderDrinkRepository.deleteByIdOrderId(orderId);
        }
        else {
            throw new ResourceNotFoundException("Resource: OrderDrink. Not found with order id: " + orderId);
        }
    }
}
