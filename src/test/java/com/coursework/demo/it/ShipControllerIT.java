package com.coursework.demo.it;

import com.coursework.demo.dto.ShipDTO;
import com.coursework.demo.entity.Ship;
import com.coursework.demo.repository.ShipRepository;
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

import static com.coursework.demo.TestData.getShip;
import static com.coursework.demo.TestData.getShipRequest;
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
public class ShipControllerIT {

    private static final String SHIP_CONTROLLER_PATH = "/v1/ships/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipRepository shipRepository;

    @Test
    public void testRetrieveShipById() throws Exception {
        when(shipRepository.findById(anyLong())).thenReturn(Optional.of(getShip()));

        mockMvc.perform(getRequest(SHIP_CONTROLLER_PATH + "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getShipRequest())));
    }

    @Test
    public void testRetrieveShipList() throws Exception {
        final Ship ship = getShip();
        final List<Ship> ships = Collections.singletonList(ship);
        final Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        final Page<Ship> shipPage = new PageImpl<>(ships, pageable, 10);

        when(shipRepository.findAll(pageable)).thenReturn(shipPage);

        mockMvc.perform(getRequest(SHIP_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.singletonList(getShipRequest()))));
    }

    @Test
    public void testSaveShip() throws Exception {
        final Ship ship = getShip();
        final ShipDTO request = getShipRequest();

        when(shipRepository.save(any(Ship.class))).thenReturn(ship);

        mockMvc.perform(postRequest(SHIP_CONTROLLER_PATH, request))
                .andExpect(status().isCreated())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateShip() throws Exception {
        final Ship ship = getShip();
        final ShipDTO request = getShipRequest();

        when(shipRepository.save(ship)).thenReturn(ship);

        mockMvc.perform(putRequest(SHIP_CONTROLLER_PATH + "1", request))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateShipExpectedBadRequest() throws Exception {
        final Ship ship = getShip();
        final ShipDTO request = getShipRequest();

        when(shipRepository.save(ship)).thenReturn(ship);

        mockMvc.perform(putRequest(SHIP_CONTROLLER_PATH + "2", request))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteShip() throws Exception {
        final Ship ship = getShip();

        when(shipRepository.findById(anyLong())).thenReturn(Optional.of(ship));
        doNothing().when(shipRepository).delete(ship);

        mockMvc.perform(deleteRequest(SHIP_CONTROLLER_PATH + "1"))
                .andExpect(status().isNoContent());
    }
}
