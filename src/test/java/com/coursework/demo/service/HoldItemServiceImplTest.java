package com.coursework.demo.service;

import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.entity.HoldItem;
import com.coursework.demo.entity.enums.HoldType;
import com.coursework.demo.repository.HoldItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HoldItemServiceImplTest {

    @MockBean
    private HoldItemRepository holdItemRepository;

    @Autowired
    private HoldItemService holdItemService;

    @Test
    public void testGetById() {
        final HoldItem holdItem = getHoldItem();

        when(holdItemRepository.findById(anyLong())).thenReturn(Optional.of(holdItem));

        final HoldItem result = holdItemService.getById(1L);

        assertEquals(holdItem, result);
        verify(holdItemRepository).findById(anyLong());
    }

    @Test
    public void testFindHoldItemQuantity() {
        final List<HoldTypeDTO> holdItemDTOList =
                Collections.singletonList(HoldTypeDTO.builder().name(HoldType.ALCOHOL).quantity(20L).build());

        when(holdItemRepository.findHoldItemQuantity("Victory")).thenReturn(holdItemDTOList);

        final List<HoldTypeDTO> result = holdItemService.findHoldItemQuantity("Victory");

        assertEquals(holdItemDTOList, result);
        verify(holdItemRepository).findHoldItemQuantity("Victory");
    }

    @Test
    public void testGetAll() {
        final HoldItem holdItem = getHoldItem();
        final List<HoldItem> holdItems = Collections.singletonList(holdItem);
        final Pageable pageable = PageRequest.of(0, 5);
        final Page<HoldItem> holdItemPage = new PageImpl<>(holdItems, pageable, 5);

        when(holdItemRepository.findAll(pageable)).thenReturn(holdItemPage);

        final List<HoldItem> result = holdItemService.getAll(pageable);

        assertEquals(holdItems, result);
        verify(holdItemRepository).findAll(pageable);
    }

    @Test
    public void testSave() {
        final HoldItem holdItem = getHoldItem();

        when(holdItemRepository.save(holdItem)).thenReturn(holdItem);

        final HoldItem result = holdItemService.save(holdItem);

        assertEquals(holdItem, result);
        verify(holdItemRepository).save(holdItem);
    }

    @Test
    public void testDelete() {
        final HoldItem holdItem = getHoldItem();

        doNothing().when(holdItemRepository).delete(holdItem);

        final HoldItem result = holdItemService.delete(holdItem);

        assertEquals(holdItem, result);
        verify(holdItemRepository).delete(holdItem);
    }

    private HoldItem getHoldItem() {
        return HoldItem.builder()
                .holdType(HoldType.ALCOHOL)
                .name("Jack Daniels")
                .quantity(10)
                .build();
    }
}
