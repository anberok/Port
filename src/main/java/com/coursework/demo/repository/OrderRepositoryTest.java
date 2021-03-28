package com.coursework.demo.repository;

import com.coursework.demo.entity.Crew;
import com.coursework.demo.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static com.coursework.demo.entity.enums.OrderStatus.ANALYSIS;
import static com.coursework.demo.entity.enums.OrderStatus.DONE;
import static com.coursework.demo.entity.enums.OrderStatus.REJECTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private List<Order> getExpectedOrderList() {
        Crew crew = Crew.builder().id(1L).captain("Sparrow").nationality("Spanish").size(50).build();
        return Arrays.asList(
                Order.builder()
                        .id(1L)
                        .client("France")
                        .description("sink 5 english ships")
                        .orderStatus(DONE)
                        .reward(200)
                        .crew(crew)
                        .build(),
                Order.builder()
                        .id(2L)
                        .client("England")
                        .description("sink 5 france ships")
                        .orderStatus(REJECTED)
                        .reward(150)
                        .crew(crew)
                        .build(),
                Order.builder()
                        .id(3L)
                        .client("Spanish")
                        .description("sink 3 english ships")
                        .orderStatus(ANALYSIS)
                        .reward(100)
                        .crew(crew)
                        .build()
        );
    }
}
