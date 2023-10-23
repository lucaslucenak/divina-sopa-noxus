package com.lucalucenak.Noxus.services;

import com.lucalucenak.Noxus.dtos.OrderProductFullDto;
import com.lucalucenak.Noxus.exceptions.ResourceNotFoundException;
import com.lucalucenak.Noxus.models.OrderProductModel;
import com.lucalucenak.Noxus.models.pks.OrderProductPk;
import com.lucalucenak.Noxus.repositories.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DrinkService drinkService;

    @Transactional
    public OrderProductFullDto findOrderProductById(OrderProductPk orderProductPk) {
        Optional<OrderProductModel> orderProductOptional = orderProductRepository.findById(orderProductPk);

        if (orderProductOptional.isPresent()) {
            return new OrderProductFullDto(orderProductOptional.get());
        } else {
            throw new ResourceNotFoundException("Resource: OrderProduct. Not found with order id: "+ orderProductPk.getOrder().getId() + " and product id: " + orderProductPk.getProduct().getId());
        }
    }


    @Transactional
    public List<OrderProductFullDto> findOrderProductsByOrderId(Long orderId) {
        List<Optional<OrderProductModel>> orderProductsOptional = orderProductRepository.findByIdOrderId(orderId);

        if (orderProductsOptional.size() < 1) {
            throw new ResourceNotFoundException("Resource: OrderProduct. Not found with order id: " + orderId);
        } else {
            List<OrderProductFullDto> orderProductFullDtos = new ArrayList<>();
            for (Optional<OrderProductModel> i : orderProductsOptional) {
                orderProductFullDtos.add(new OrderProductFullDto(i.get()));
            }
            return orderProductFullDtos;
        }
    }

    @Transactional
    public Page<OrderProductFullDto> findAllOrderProductsPaginated(Pageable pageable) {
        Page<OrderProductModel> pagedOrderProducts = orderProductRepository.findAll(pageable);
        return pagedOrderProducts.map(OrderProductFullDto::new);
    }

    @Transactional
    public OrderProductFullDto saveOrderProduct(OrderProductFullDto orderProductFullDto) {
        OrderProductModel orderProductModel = new OrderProductModel(orderProductFullDto);
        return new OrderProductFullDto(orderProductRepository.save(orderProductModel));
    }

    @Transactional
    public void deleteOrderProductByOrderId(Long orderId) {
        if (orderProductRepository.existsByIdOrderId(orderId)) {
            orderProductRepository.deleteByIdOrderId(orderId);
        }
        else {
            throw new ResourceNotFoundException("Resource: OrderProduct. Not found with order id: " + orderId);
        }
    }
}
