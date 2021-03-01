package com.coursework.demo.mapper;

import com.coursework.demo.dto.AddHoldItemDTO;
import com.coursework.demo.dto.HoldItemDTO;
import com.coursework.demo.entity.HoldItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HoldItemMapper {

    HoldItemDTO convertToDto(HoldItem holdItem);

    HoldItem convertToEntity(HoldItemDTO holdItemDTO);

    HoldItem convertToEntity(AddHoldItemDTO holdItemDTO);

    List<HoldItemDTO> convertToDtoList(List<HoldItem> holdItems);

}