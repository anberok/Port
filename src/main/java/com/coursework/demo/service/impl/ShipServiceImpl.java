package com.coursework.demo.service.impl;

import com.coursework.demo.entity.Ship;
import com.coursework.demo.repository.ShipRepository;
import com.coursework.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ShipServiceImpl implements ShipService {

    ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public Ship getById(Long id) {
        return shipRepository.findById(id).get();
    }

    @Override
    public List<Ship> getAll(Pageable pageable) {
        return shipRepository.findAll(pageable).getContent();
    }

    @Override
    public Ship save(Ship object) {
        return shipRepository.save(object);
    }

    @Override
    public Ship delete(Ship object) {
        shipRepository.delete(object);
        return object;
    }
}
