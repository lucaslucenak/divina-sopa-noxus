package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.OrderSoupFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.OrderModel;
import com.lucalucenak.Noxus.models.OrderSoupModel;
import com.lucalucenak.Noxus.models.SoupModel;
import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
import com.lucalucenak.Noxus.repositories.OrderSoupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderSoupService {

    @Autowired
    private OrderSoupRepository orderSoupRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SoupService soupService;

    @Transactional
    public OrderSoupFullDto findOrderSoupById(OrderSoupPk orderSoupPk) {
        Optional<OrderSoupModel> orderSoupOptional = orderSoupRepository.findById(orderSoupPk);

        if (orderSoupOptional.isPresent()) {
            return new OrderSoupFullDto(orderSoupOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: OrderSoup. Not found with order id: "+ orderSoupPk.getOrder().getId() + " and soup id: " + orderSoupPk.getSoup().getId());
        }
    }

    @Transactional
    public List<OrderSoupFullDto> findOrderSoupsByOrderId(Long orderId) {
        List<Optional<OrderSoupModel>> orderSoupsOptional = orderSoupRepository.findByIdOrderId(orderId);

        if (orderSoupsOptional.size() < 1) {
            throw new ResourceNotFoundException("Resource: OrderSoup. Not found with order id: " + orderId);
        } else {
            List<OrderSoupFullDto> orderSoupFullDtos = new ArrayList<>();
            for (Optional<OrderSoupModel> i : orderSoupsOptional) {
                orderSoupFullDtos.add(new OrderSoupFullDto(i.get()));
            }
            return orderSoupFullDtos;
        }
    }

    @Transactional
    public Page<OrderSoupFullDto> findAllOrderSoupsPaginated(Pageable pageable) {
        Page<OrderSoupModel> pagedOrderSoups = orderSoupRepository.findAll(pageable);
        return pagedOrderSoups.map(OrderSoupFullDto::new);
    }

    @Transactional
    public OrderSoupFullDto saveOrderSoup(OrderSoupFullDto orderSoupFullDto) {
        OrderSoupModel orderSoupModel = new OrderSoupModel(orderSoupFullDto);
        return new OrderSoupFullDto(orderSoupRepository.save(orderSoupModel));
    }

    @Transactional
    public void deleteOrderSoupByOrderId(Long orderId) {
        if (orderSoupRepository.existsByIdOrderId(orderId)) {
            orderSoupRepository.deleteByIdOrderId(orderId);
        }
        else {
            throw new ResourceNotFoundException("Resource: OrderSoup. Not found with order id: " + orderId);
        }
    }
}
