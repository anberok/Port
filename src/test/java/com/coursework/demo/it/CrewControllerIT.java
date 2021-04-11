package com.coursework.demo.it;

import com.coursework.demo.dto.CrewDTO;
import com.coursework.demo.entity.Crew;
import com.coursework.demo.repository.CrewRepository;
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

import static com.coursework.demo.TestData.getCrew;
import static com.coursework.demo.TestData.getCrewRequest;
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
public class CrewControllerIT {

    private static final String CREW_CONTROLLER_PATH = "/v1/crews/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CrewRepository crewRepository;

    @Test
    public void testRetrieveCrewById() throws Exception {
        when(crewRepository.findById(anyLong())).thenReturn(Optional.of(getCrew()));

        mockMvc.perform(getRequest(CREW_CONTROLLER_PATH + "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(getCrewRequest())));
    }

    @Test
    public void testRetrieveCrewList() throws Exception {
        final Crew crew = getCrew();
        final List<Crew> crews = Collections.singletonList(crew);
        final Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        final Page<Crew> crewPage = new PageImpl<>(crews, pageable, 10);

        when(crewRepository.findAll(pageable)).thenReturn(crewPage);

        mockMvc.perform(getRequest(CREW_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.singletonList(getCrewRequest()))));
    }

    @Test
    public void testSaveCrew() throws Exception {
        final Crew crew = getCrew();
        final CrewDTO request = getCrewRequest();

        when(crewRepository.save(any(Crew.class))).thenReturn(crew);

        mockMvc.perform(postRequest(CREW_CONTROLLER_PATH, request))
                .andExpect(status().isCreated())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateCrew() throws Exception {
        final Crew crew = getCrew();
        final CrewDTO request = getCrewRequest();

        when(crewRepository.save(crew)).thenReturn(crew);

        mockMvc.perform(putRequest(CREW_CONTROLLER_PATH + "1", request))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(request)));
    }

    @Test
    public void testUpdateCrewExpectedBadRequest() throws Exception {
        final Crew crew = getCrew();
        final CrewDTO request = getCrewRequest();

        when(crewRepository.save(crew)).thenReturn(crew);

        mockMvc.perform(putRequest(CREW_CONTROLLER_PATH + "2", request))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testDeleteCrew() throws Exception {
        final Crew crew = getCrew();

        when(crewRepository.findById(anyLong())).thenReturn(Optional.of(crew));
        doNothing().when(crewRepository).delete(crew);

        mockMvc.perform(deleteRequest(CREW_CONTROLLER_PATH + "1"))
                .andExpect(status().isNoContent());
    }
}
