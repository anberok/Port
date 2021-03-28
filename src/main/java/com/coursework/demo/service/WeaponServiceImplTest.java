package com.coursework.demo.service;

import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.dto.WeaponsTypeDTO;
import com.coursework.demo.entity.Weapon;
import com.coursework.demo.entity.enums.HoldType;
import com.coursework.demo.entity.enums.WeaponsType;
import com.coursework.demo.entity.enums.Wear;
import com.coursework.demo.repository.WeaponRepository;
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
public class WeaponServiceImplTest {
    @MockBean
    private WeaponRepository weaponRepository;

    @Autowired
    private WeaponService weaponService;

    @Test
    public void testGetById() {
        final Weapon weapon = getWeapon();

        when(weaponRepository.findById(anyLong())).thenReturn(Optional.of(weapon));

        final Weapon result = weaponService.getById(1L);

        assertEquals(weapon, result);
        verify(weaponRepository).findById(anyLong());
    }

    @Test
    public void testFindHoldItemQuantity() {
        final List<WeaponsTypeDTO> weaponsTypeDTOList =
                Collections.singletonList(WeaponsTypeDTO.builder().name(WeaponsType.MUSKET).quantity(20L).build());

        when(weaponRepository.findWeaponsTypeQuantity("Victory")).thenReturn(weaponsTypeDTOList);

        final List<WeaponsTypeDTO> result = weaponService.findWeaponsTypeQuantity("Victory");

        assertEquals(weaponsTypeDTOList, result);
        verify(weaponRepository).findWeaponsTypeQuantity("Victory");
    }

    @Test
    public void testGetAll() {
        final Weapon weapon = getWeapon();
        final List<Weapon> weapons = Collections.singletonList(weapon);
        final Pageable pageable = PageRequest.of(0, 5);
        final Page<Weapon> weaponPage = new PageImpl<>(weapons, pageable, 5);

        when(weaponRepository.findAll(pageable)).thenReturn(weaponPage);

        final List<Weapon> result = weaponService.getAll(pageable);

        assertEquals(weapons, result);
        verify(weaponRepository).findAll(pageable);
    }

    @Test
    public void testSave() {
        final Weapon weapon = getWeapon();

        when(weaponRepository.save(weapon)).thenReturn(weapon);

        final Weapon result = weaponService.save(weapon);

        assertEquals(weapon, result);
        verify(weaponRepository).save(weapon);
    }

    @Test
    public void testDelete() {
        final Weapon weapon = getWeapon();

        doNothing().when(weaponRepository).delete(weapon);

        final Weapon result = weaponService.delete(weapon);

        assertEquals(weapon, result);
        verify(weaponRepository).delete(weapon);
    }

    private Weapon getWeapon() {
        return Weapon.builder()
                .quantity(5)
                .weaponsType(WeaponsType.BLUNDERBUS)
                .wear(Wear.MINIMAL_WEAR)
                .build();
    }
}

