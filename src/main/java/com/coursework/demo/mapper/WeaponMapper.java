package com.coursework.demo.mapper;

import com.coursework.demo.dto.AddWeaponDTO;
import com.coursework.demo.dto.WeaponDTO;
import com.coursework.demo.entity.Weapon;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeaponMapper {

    WeaponDTO convertToDto(Weapon weapon);

    Weapon convertToEntity(WeaponDTO weaponDTO);

    Weapon convertToEntity(AddWeaponDTO weaponDTO);

    List<WeaponDTO> convertToDtoList(List<Weapon> weapons);

}