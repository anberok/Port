package com.coursework.demo.service;

import com.coursework.demo.entity.Crew;
import com.coursework.demo.repository.CrewRepository;
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
public class CrewServiceImplTest {

    @MockBean
    private CrewRepository crewRepository;

    @Autowired
    private CrewService crewService;

    @Test
    public void testGetById() {
        final Crew crew = getCrew();

        when(crewRepository.findById(anyLong())).thenReturn(Optional.of(crew));

        final Crew result = crewService.getById(1L);

        assertEquals(crew, result);
        verify(crewRepository).findById(anyLong());
    }

    @Test
    public void testGetAll() {
        final Crew crew = getCrew();
        final List<Crew> crews = Collections.singletonList(crew);
        final Pageable pageable = PageRequest.of(0, 5);
        final Page<Crew> crewPage = new PageImpl<>(crews, pageable, 5);

        when(crewRepository.findAll(pageable)).thenReturn(crewPage);

        final List<Crew> result = crewService.getAll(pageable);

        assertEquals(crews, result);
        verify(crewRepository).findAll(pageable);
    }

    @Test
    public void testSave() {
        final Crew crew = getCrew();

        when(crewRepository.save(crew)).thenReturn(crew);

        final Crew result = crewService.save(crew);

        assertEquals(crew, result);
        verify(crewRepository).save(crew);
    }

    @Test
    public void testDelete() {
        final Crew crew = getCrew();

        doNothing().when(crewRepository).delete(crew);

        final Crew result = crewService.delete(crew);

        assertEquals(crew, result);
        verify(crewRepository).delete(crew);
    }

    private Crew getCrew() {
        return Crew.builder()
                .captain("Sparrow")
                .size(50)
                .nationality("Spanish")
                .build();
    }
}
