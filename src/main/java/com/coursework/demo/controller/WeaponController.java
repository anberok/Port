package com.coursework.demo.controller;

import com.coursework.demo.dto.AddWeaponDTO;
import com.coursework.demo.dto.WeaponDTO;
import com.coursework.demo.dto.WeaponsTypeDTO;
import com.coursework.demo.entity.Weapon;
import com.coursework.demo.mapper.WeaponMapper;
import com.coursework.demo.service.WeaponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Weapon API")
@RequestMapping("/v1/weapons")
public class WeaponController {

    private final WeaponService weaponService;
    private final WeaponMapper weaponMapper;

    @Autowired
    public WeaponController(WeaponService weaponService, WeaponMapper weaponMapper) {
        this.weaponService = weaponService;
        this.weaponMapper = weaponMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get weapon info by id")
    public ResponseEntity<WeaponDTO> get(@PathVariable("id") long id) {
        Weapon weapon = weaponService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(weaponMapper.convertToDto(weapon));
    }

    @GetMapping("/stats")
    @ApiOperation(value = "Get weapon type stats")
    public ResponseEntity<List<WeaponsTypeDTO>> getStats(@RequestParam String shipName) {
        List<WeaponsTypeDTO> weaponsTypeDTOList = weaponService.findWeaponsTypeQuantity(shipName);
        return ResponseEntity.status(HttpStatus.OK).body(weaponsTypeDTOList);
    }


    @GetMapping
    @ApiOperation(value = "Get the list of all weapons")
    public ResponseEntity<List<WeaponDTO>> getAll(@PageableDefault(sort = {"id"}) Pageable pageable) {
        return ResponseEntity.ok().body(weaponMapper.convertToDtoList(weaponService.getAll(pageable)));
    }


    @PostMapping
    @ApiOperation(value = "Create new weapon")
    public ResponseEntity<WeaponDTO> save(@RequestBody AddWeaponDTO addWeaponDTO) {
        Weapon weapon = weaponService.save(weaponMapper.convertToEntity(addWeaponDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(weaponMapper.convertToDto(weapon));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update existing weapon by id")
    public ResponseEntity<WeaponDTO> update(@PathVariable("id") long id, @RequestBody WeaponDTO weaponDTO) {
        if (id == weaponDTO.getId()) {
            Weapon weapon = weaponService.save(weaponMapper.convertToEntity(weaponDTO));
            return ResponseEntity.status(HttpStatus.OK).body(weaponMapper.convertToDto(weapon));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete weapon by id")
    public ResponseEntity delete(@PathVariable("id") long id) {
        Weapon weapon = weaponService.getById(id);
        weaponService.delete(weapon);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
