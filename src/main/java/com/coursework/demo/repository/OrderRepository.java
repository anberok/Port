package com.coursework.demo.repository;

import com.coursework.demo.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    @Query("SELECT o FROM Order o\n" +
           "JOIN o.crew c\n" +
           "WHERE c.captain = :captain\n")
    List<Order> findAllByCaptain(String captain);
}
