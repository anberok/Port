package com.coursework.demo.dto;

import com.coursework.demo.entity.Ship;
import com.coursework.demo.entity.enums.HoldType;
import lombok.Data;

@Data
public class HoldItemDTO {

    private long id;

    private String name;

    private int quantity;

    private HoldType holdType;

    private Ship ship;
}
