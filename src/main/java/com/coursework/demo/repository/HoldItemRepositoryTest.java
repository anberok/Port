package com.coursework.demo.repository;

import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.entity.enums.HoldType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static com.coursework.demo.entity.enums.HoldType.*;
import static com.coursework.demo.entity.enums.HoldType.PROVISIONS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class HoldItemRepositoryTest {

    @Autowired
    private HoldItemRepository holdItemRepository;

    @Test
    @Sql("/findHoldItemQuantityTest.sql")
    public void testFindHoldItemQuantity() {
        List<HoldTypeDTO> result = holdItemRepository.findHoldItemQuantity("Victory");

        assertFalse(result.isEmpty());
        assertTrue(result.containsAll(getExpectedHoldTypeDTOList()));
    }

    private List<HoldTypeDTO> getExpectedHoldTypeDTOList() {
        return Arrays.asList(
                HoldTypeDTO.builder().name(PROVISIONS).quantity(100L).build(),
                HoldTypeDTO.builder().name(CLOTH).quantity(400L).build(),
                HoldTypeDTO.builder().name(ALCOHOL).quantity(500L).build(),
                HoldTypeDTO.builder().name(JEWELLERY).quantity(100L).build()
        );
    }
}
