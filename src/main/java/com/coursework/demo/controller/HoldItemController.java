package com.coursework.demo.controller;

import com.coursework.demo.dto.AddHoldItemDTO;
import com.coursework.demo.dto.HoldItemDTO;
import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.entity.HoldItem;
import com.coursework.demo.mapper.HoldItemMapper;
import com.coursework.demo.service.HoldItemService;
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
@Api(tags = "HoldItem API")
@RequestMapping("/v1/hold_items")
public class HoldItemController {

    private final HoldItemService holdItemService;
    private final HoldItemMapper holdItemMapper;

    @Autowired
    public HoldItemController(HoldItemService holdItemService, HoldItemMapper holdItemMapper) {
        this.holdItemService = holdItemService;
        this.holdItemMapper = holdItemMapper;
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get hold item info by id")
    public ResponseEntity<HoldItemDTO> get(@PathVariable("id") long id) {
        HoldItem holdItem = holdItemService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(holdItemMapper.convertToDto(holdItem));
    }

    @GetMapping("/stats")
    @ApiOperation(value = "Get the list of hold item stats")
    public ResponseEntity<List<HoldTypeDTO>> getStats(@RequestParam String shipName) {
        List<HoldTypeDTO> holdTypeDTOList = holdItemService.findHoldItemQuantity(shipName);
        return ResponseEntity.status(HttpStatus.OK).body(holdTypeDTOList);
    }


    @GetMapping
    @ApiOperation(value = "Get the list of all hold items")
    public ResponseEntity<List<HoldItemDTO>> getAll(@PageableDefault(sort = {"id"}) Pageable pageable) {
        return ResponseEntity.ok().body(holdItemMapper.convertToDtoList(holdItemService.getAll(pageable)));
    }


    @PostMapping
    @ApiOperation(value = "Create new hold item")
    public ResponseEntity<HoldItemDTO> save(@RequestBody AddHoldItemDTO addHoldItemDTO) {
        HoldItem holdItem = holdItemService.save(holdItemMapper.convertToEntity(addHoldItemDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(holdItemMapper.convertToDto(holdItem));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update existing hold item by id")
    public ResponseEntity<HoldItemDTO> update(@PathVariable("id") long id, @RequestBody HoldItemDTO holdItemDTO) {
        if (id == holdItemDTO.getId()) {
            HoldItem holdItem = holdItemService.save(holdItemMapper.convertToEntity(holdItemDTO));
            return ResponseEntity.status(HttpStatus.OK).body(holdItemMapper.convertToDto(holdItem));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete hold item by id")
    public ResponseEntity delete(@PathVariable("id") long id) {
        HoldItem holdItem = holdItemService.getById(id);
        holdItemService.delete(holdItem);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
