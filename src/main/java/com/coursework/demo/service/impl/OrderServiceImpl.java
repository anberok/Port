package com.coursework.demo.service.impl;

import com.coursework.demo.entity.Order;
import com.coursework.demo.repository.OrderRepository;
import com.coursework.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable).getContent();
    }

    @Override
    public Order save(Order object) {
        return orderRepository.save(object);
    }

    @Override
    public Order delete(Order object) {
        orderRepository.delete(object);
        return object;
    }

    @Override
    public List<Order> findAllByCaptain(String captain) {
        return orderRepository.findAllByCaptain(captain);
    }
}
