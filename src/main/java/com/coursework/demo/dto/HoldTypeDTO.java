package com.coursework.demo.dto;

import com.coursework.demo.entity.enums.HoldType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HoldTypeDTO {
    private HoldType name;

    private Long quantity;
}
