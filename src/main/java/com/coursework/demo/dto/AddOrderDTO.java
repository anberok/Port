package com.coursework.demo.dto;

import com.coursework.demo.entity.Crew;
import com.coursework.demo.entity.enums.OrderStatus;
import lombok.Data;

@Data
public class AddOrderDTO {
    private String description;

    private String client;

    private int reward;

    private Crew crew;

    private OrderStatus orderStatus;
}
