package com.coursework.demo.dto;

import com.coursework.demo.entity.enums.HoldType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HoldTypeDTO {
    private HoldType name;

    private Long quantity;
}
