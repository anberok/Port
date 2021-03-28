package com.coursework.demo.repository;

import com.coursework.demo.dto.WeaponsTypeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static com.coursework.demo.entity.enums.WeaponsType.CANNON;
import static com.coursework.demo.entity.enums.WeaponsType.CUTLASS;
import static com.coursework.demo.entity.enums.WeaponsType.MUSKET;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class WeaponRepositoryTest {
    @Autowired
    private WeaponRepository weaponRepository;

    @Test
    @Sql("/findWeaponsTypeQuantityTest.sql")
    public void testFindWeaponsTypeQuantity() {
        List<WeaponsTypeDTO> result = weaponRepository.findWeaponsTypeQuantity("Victory");

        assertFalse(result.isEmpty());
        assertTrue(result.containsAll(getExpectedWeaponsTypeDTOList()));
    }

    private List<WeaponsTypeDTO> getExpectedWeaponsTypeDTOList() {
        return Arrays.asList(
                WeaponsTypeDTO.builder().name(MUSKET).quantity(110L).build(),
                WeaponsTypeDTO.builder().name(CANNON).quantity(10L).build(),
                WeaponsTypeDTO.builder().name(CUTLASS).quantity(5L).build()
        );
    }

}
