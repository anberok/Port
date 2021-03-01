package com.coursework.demo.service;

import com.coursework.demo.dto.WeaponsTypeDTO;
import com.coursework.demo.entity.Weapon;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeaponService extends BasicService<Weapon, Long> {
    List<WeaponsTypeDTO> findWeaponsTypeQuantity(@Param("name") String shipName);
}