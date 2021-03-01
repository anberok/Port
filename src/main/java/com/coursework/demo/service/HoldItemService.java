package com.coursework.demo.service;

import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.entity.HoldItem;

import java.util.List;

public interface HoldItemService extends BasicService<HoldItem, Long> {
    List<HoldTypeDTO> findHoldItemQuantity(String shipName);
}
