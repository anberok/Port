package com.coursework.demo.it;

import com.coursework.demo.dto.HoldItemDTO;
import com.coursework.demo.entity.HoldItem;
import com.coursework.demo.repository.HoldItemRepository;
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

import static com.coursework.demo.TestData.getExpectedHoldTypeDTOList;
import static com.coursework.demo.TestData.getHoldItem;
import static com.coursework.demo.TestData.getHoldItemRequest;
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
public class HoldItemControllerIT {

    private static final String HOLD_ITEMS_CONTROLLER_PATH = "/v1/hold_items/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HoldItemRepository holdItemRepository;

    @Test
    public void testRetrieveHoldItemById() throws Exception {
        when(holdItemRepository.findById(anyLong())).thenReturn(Optional.of(getHoldItem()));

        mockMvc.perform(getRequest(HOLD_ITEMS_CONTROLLER_PATH + "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getHoldItemRequest())));
    }

    @Test
    public void testRetrieveStats() throws Exception {
        when(holdItemRepository.findHoldItemQuantity("Victory")).thenReturn(getExpectedHoldTypeDTOList());

        mockMvc.perform(getRequest(HOLD_ITEMS_CONTROLLER_PATH + "stats").param("shipName", "Victory"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getExpectedHoldTypeDTOList())));
    }

    @Test
    public void testRetrieveHoldItemList() throws Exception {
        final HoldItem holdItem = getHoldItem();
        final List<HoldItem> holdItems = Collections.singletonList(holdItem);
        final Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        final Page<HoldItem> holdItemPage = new PageImpl<>(holdItems, pageable, 10);

        when(holdItemRepository.findAll(pageable)).thenReturn(holdItemPage);

        mockMvc.perform(getRequest(HOLD_ITEMS_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.singletonList(getHoldItemRequest()))));
    }

    @Test
    public void testSaveHoldItem() throws Exception {
        final HoldItem holdItem = getHoldItem();
        final HoldItemDTO request = getHoldItemRequest();

        when(holdItemRepository.save(any(HoldItem.class))).thenReturn(holdItem);

        mockMvc.perform(postRequest(HOLD_ITEMS_CONTROLLER_PATH, request))
                .andExpect(status().isCreated())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateHoldItem() throws Exception {
        final HoldItem holdItem = getHoldItem();
        final HoldItemDTO request = getHoldItemRequest();

        when(holdItemRepository.save(holdItem)).thenReturn(holdItem);

        mockMvc.perform(putRequest(HOLD_ITEMS_CONTROLLER_PATH + "1", request))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateHoldItemExpectedBadRequest() throws Exception {
        final HoldItem holdItem = getHoldItem();
        final HoldItemDTO request = getHoldItemRequest();

        when(holdItemRepository.save(holdItem)).thenReturn(holdItem);

        mockMvc.perform(putRequest(HOLD_ITEMS_CONTROLLER_PATH + "2", request))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteHoldItem() throws Exception {
        final HoldItem holdItem = getHoldItem();

        when(holdItemRepository.findById(anyLong())).thenReturn(Optional.of(holdItem));
        doNothing().when(holdItemRepository).delete(holdItem);

        mockMvc.perform(deleteRequest(HOLD_ITEMS_CONTROLLER_PATH + "1"))
                .andExpect(status().isNoContent());
    }
}
