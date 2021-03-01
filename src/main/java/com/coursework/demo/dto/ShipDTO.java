package com.coursework.demo.dto;

import com.coursework.demo.entity.Crew;
import com.coursework.demo.entity.enums.ShipType;
import lombok.Data;

@Data
public class ShipDTO {
    private long id;

    private String name;

    private int speed;

    private Crew crew;

    private ShipType shipType;

}
