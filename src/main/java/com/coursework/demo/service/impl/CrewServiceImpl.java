package com.coursework.demo.service.impl;

import com.coursework.demo.entity.Crew;
import com.coursework.demo.entity.Order;
import com.coursework.demo.repository.CrewRepository;
import com.coursework.demo.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CrewServiceImpl implements CrewService {

    CrewRepository crewRepository;

    @Autowired
    public CrewServiceImpl(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    @Override
    public Crew getById(Long id) {
        return crewRepository.findById(id).get();
    }

    @Override
    public List<Crew> getAll(Pageable pageable) {
        return crewRepository.findAll(pageable).getContent();
    }

    @Override
    public Crew save(Crew object) {
        return crewRepository.save(object);
    }

    @Override
    public Crew delete(Crew object) {
        crewRepository.delete(object);
        return object;
    }
}
