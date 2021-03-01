package com.coursework.demo.dto;

import com.coursework.demo.entity.Ship;
import com.coursework.demo.entity.enums.WeaponsType;
import com.coursework.demo.entity.enums.Wear;
import lombok.Data;

@Data
public class AddWeaponDTO {

    private String name;

    private int quantity;

    private Wear wear;

    private WeaponsType weaponsType;

    private Ship ship;
}
