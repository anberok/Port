package com.coursework.demo.it;

import com.coursework.demo.dto.WeaponDTO;
import com.coursework.demo.entity.Weapon;
import com.coursework.demo.repository.WeaponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.coursework.demo.TestData.getExpectedWeaponsTypeDTOList;
import static com.coursework.demo.TestData.getWeapon;
import static com.coursework.demo.TestData.getWeaponRequest;
import static com.coursework.demo.it.TestUtils.asJsonString;
import static com.coursework.demo.it.TestUtils.deleteRequest;
import static com.coursework.demo.it.TestUtils.getRequest;
import static com.coursework.demo.it.TestUtils.postRequest;
import static com.coursework.demo.it.TestUtils.putRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WeaponControllerIT {

    private static final String WEAPON_CONTROLLER_PATH = "/v1/weapons/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeaponRepository weaponRepository;

    @Test
    public void testRetrieveWeaponById() throws Exception {
        when(weaponRepository.findById(anyLong())).thenReturn(Optional.of(getWeapon()));

        mockMvc.perform(getRequest(WEAPON_CONTROLLER_PATH + "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getWeaponRequest())));
    }

    @Test
    public void testRetrieveStats() throws Exception {
        when(weaponRepository.findWeaponsTypeQuantity("Victory")).thenReturn(getExpectedWeaponsTypeDTOList());

        mockMvc.perform(getRequest(WEAPON_CONTROLLER_PATH + "stats").param("shipName", "Victory"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getExpectedWeaponsTypeDTOList())));
    }

    @Test
    public void testRetrieveWeaponList() throws Exception {
        final Weapon weapon = getWeapon();
        final List<Weapon> weapons = Collections.singletonList(weapon);
        final Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        final Page<Weapon> weaponPage = new PageImpl<>(weapons, pageable, 10);

        when(weaponRepository.findAll(pageable)).thenReturn(weaponPage);

        mockMvc.perform(getRequest(WEAPON_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.singletonList(getWeaponRequest()))));
    }

    @Test
    public void testSaveWeapon() throws Exception {
        final Weapon weapon = getWeapon();
        final WeaponDTO request = getWeaponRequest();

        when(weaponRepository.save(any(Weapon.class))).thenReturn(weapon);

        mockMvc.perform(postRequest(WEAPON_CONTROLLER_PATH, request))
                .andExpect(status().isCreated())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateWeapon() throws Exception {
        final Weapon weapon = getWeapon();
        final WeaponDTO request = getWeaponRequest();

        when(weaponRepository.save(weapon)).thenReturn(weapon);

        mockMvc.perform(putRequest(WEAPON_CONTROLLER_PATH + "1", request))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateWeaponExpectedBadRequest() throws Exception {
        final Weapon weapon = getWeapon();
        final WeaponDTO request = getWeaponRequest();

        when(weaponRepository.save(weapon)).thenReturn(weapon);

        mockMvc.perform(putRequest(WEAPON_CONTROLLER_PATH + "2", request))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteWeapon() throws Exception {
        final Weapon weapon = getWeapon();

        when(weaponRepository.findById(anyLong())).thenReturn(Optional.of(weapon));
        doNothing().when(weaponRepository).delete(weapon);

        mockMvc.perform(deleteRequest(WEAPON_CONTROLLER_PATH + "1"))
                .andExpect(status().isNoContent());
    }
}