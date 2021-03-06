package com.coursework.demo.dto;

import com.coursework.demo.entity.Ship;
import com.coursework.demo.entity.enums.HoldType;
import lombok.Data;

@Data
public class AddHoldItemDTO {
    private String name;

    private int quantity;

    private HoldType holdType;

    private Ship ship;
}
