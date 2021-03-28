package com.coursework.demo.dto;

import com.coursework.demo.entity.enums.WeaponsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WeaponsTypeDTO {
    private WeaponsType name;

    private Long quantity;
}
