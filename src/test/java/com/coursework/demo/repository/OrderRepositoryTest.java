package com.coursework.demo.repository;

import com.coursework.demo.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static com.coursework.demo.TestData.getExpectedOrderList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Sql("/findAllByCaptainTest.sql")
    public void testFindOrderQuantity() {
        List<Order> result = orderRepository.findAllByCaptain("Sparrow");

        assertFalse(result.isEmpty());
        assertEquals(result, getExpectedOrderList());
    }
}
