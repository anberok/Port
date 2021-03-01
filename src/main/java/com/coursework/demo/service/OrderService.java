package com.coursework.demo.service;

import com.coursework.demo.entity.Order;

import java.util.List;

public interface OrderService extends BasicService<Order, Long> {
    List<Order> findAllByCaptain(String captain);
}