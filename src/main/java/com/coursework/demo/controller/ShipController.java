package com.coursework.demo.controller;

import com.coursework.demo.dto.AddShipDTO;
import com.coursework.demo.dto.ShipDTO;
import com.coursework.demo.entity.Ship;
import com.coursework.demo.mapper.ShipMapper;
import com.coursework.demo.service.ShipService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "Ship API")
@RequestMapping("/v1/ships")
public class ShipController {

    private final ShipService shipService;
    private final ShipMapper shipMapper;

    @Autowired
    public ShipController(ShipService shipService, ShipMapper shipMapper) {
        this.shipService = shipService;
        this.shipMapper = shipMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get ship info by id")
    public ResponseEntity<ShipDTO> get(@PathVariable("id") long id){
        Ship ship = shipService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(shipMapper.convertToDto(ship));
    }


    @GetMapping
    @ApiOperation(value = "Get the list of all ships")
    public ResponseEntity<List<ShipDTO>> getAll(@PageableDefault(sort = {"id"}) Pageable pageable) {
        return ResponseEntity.ok().body(shipMapper.convertToDtoList(shipService.getAll(pageable)));
    }


    @PostMapping
    @ApiOperation(value = "Create new ship")
    public ResponseEntity<ShipDTO> save(@RequestBody AddShipDTO addShipDTO) {
        Ship ship = shipService.save(shipMapper.convertToEntity(addShipDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(shipMapper.convertToDto(ship));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update existing ship by id")
    public ResponseEntity<ShipDTO> update(@PathVariable("id") long id, @RequestBody ShipDTO shipDTO) {
        if (id == shipDTO.getId()) {
            Ship ship = shipService.save(shipMapper.convertToEntity(shipDTO));
            return ResponseEntity.status(HttpStatus.OK).body(shipMapper.convertToDto(ship));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete ship by id")
    public ResponseEntity delete(@PathVariable("id") long id){
        Ship ship = shipService.getById(id);
        shipService.delete(ship);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
