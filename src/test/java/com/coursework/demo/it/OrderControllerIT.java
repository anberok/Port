package com.coursework.demo.it;

import com.coursework.demo.dto.OrderDTO;
import com.coursework.demo.entity.Order;
import com.coursework.demo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.coursework.demo.TestData.getExpectedOrderList;
import static com.coursework.demo.TestData.getOrder;
import static com.coursework.demo.TestData.getOrderRequest;
import static com.coursework.demo.it.TestUtils.asJsonString;
import static com.coursework.demo.it.TestUtils.deleteRequest;
import static com.coursework.demo.it.TestUtils.getRequest;
import static com.coursework.demo.it.TestUtils.postRequest;
import static com.coursework.demo.it.TestUtils.putRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderControllerIT {

    private static final String ORDER_CONTROLLER_PATH = "/v1/orders/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testRetrieveOrderById() throws Exception {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(getOrder()));

        mockMvc.perform(getRequest(ORDER_CONTROLLER_PATH + "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getOrderRequest())));
    }

    @Test
    public void testRetrieveOrdersByCaptain() throws Exception {
        when(orderRepository.findAllByCaptain("Sparrow")).thenReturn(getExpectedOrderList());

        mockMvc.perform(getRequest(ORDER_CONTROLLER_PATH + "captain").param("captain", "Sparrow"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getExpectedOrderList())));
    }

    @Test
    public void testRetrieveOrderList() throws Exception {
        final Order order = getOrder();
        final List<Order> orders = Collections.singletonList(order);
        final Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        final Page<Order> orderPage = new PageImpl<>(orders, pageable, 10);

        when(orderRepository.findAll(pageable)).thenReturn(orderPage);

        mockMvc.perform(getRequest(ORDER_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.singletonList(getOrderRequest()))));
    }

    @Test
    public void testSaveOrder() throws Exception {
        final Order order = getOrder();
        final OrderDTO request = getOrderRequest();

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(postRequest(ORDER_CONTROLLER_PATH, request))
                .andExpect(status().isCreated())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        final Order order = getOrder();
        final OrderDTO request = getOrderRequest();

        when(orderRepository.save(order)).thenReturn(order);

        mockMvc.perform(putRequest(ORDER_CONTROLLER_PATH + "1", request))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateOrderExpectedBadRequest() throws Exception {
        final Order order = getOrder();
        final OrderDTO request = getOrderRequest();

        when(orderRepository.save(order)).thenReturn(order);

        mockMvc.perform(putRequest(ORDER_CONTROLLER_PATH + "2", request))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        final Order order = getOrder();

        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).delete(order);

        mockMvc.perform(deleteRequest(ORDER_CONTROLLER_PATH + "1"))
                .andExpect(status().isNoContent());
    }
}
