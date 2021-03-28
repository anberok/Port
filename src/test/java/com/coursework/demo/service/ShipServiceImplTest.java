package com.coursework.demo.service;

import com.coursework.demo.entity.Ship;
import com.coursework.demo.entity.enums.ShipType;
import com.coursework.demo.repository.ShipRepository;
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
public class ShipServiceImplTest {
    @MockBean
    private ShipRepository shipRepository;

    @Autowired
    private ShipService shipService;

    @Test
    public void testGetById() {
        final Ship ship = getShip();

        when(shipRepository.findById(anyLong())).thenReturn(Optional.of(ship));

        final Ship result = shipService.getById(1L);

        assertEquals(ship, result);
        verify(shipRepository).findById(anyLong());
    }

    @Test
    public void testGetAll() {
        final Ship ship = getShip();
        final List<Ship> ships = Collections.singletonList(ship);
        final Pageable pageable = PageRequest.of(0, 5);
        final Page<Ship> shipPage = new PageImpl<>(ships, pageable, 5);

        when(shipRepository.findAll(pageable)).thenReturn(shipPage);

        final List<Ship> result = shipService.getAll(pageable);

        assertEquals(ships, result);
        verify(shipRepository).findAll(pageable);
    }

    @Test
    public void testSave() {
        final Ship ship = getShip();

        when(shipRepository.save(ship)).thenReturn(ship);

        final Ship result = shipService.save(ship);

        assertEquals(ship, result);
        verify(shipRepository).save(ship);
    }

    @Test
    public void testDelete() {
        final Ship ship = getShip();

        doNothing().when(shipRepository).delete(ship);

        final Ship result = shipService.delete(ship);

        assertEquals(ship, result);
        verify(shipRepository).delete(ship);
    }

    private Ship getShip() {
        return Ship.builder()
                .shipType(ShipType.BARQUE)
                .speed(200)
                .name("Victory")
                .build();
    }
}

