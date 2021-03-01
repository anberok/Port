package com.coursework.demo.service.impl;

import com.coursework.demo.dto.WeaponsTypeDTO;
import com.coursework.demo.entity.Weapon;
import com.coursework.demo.repository.WeaponRepository;
import com.coursework.demo.service.WeaponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class WeaponServiceImpl implements WeaponService {

    WeaponRepository weaponRepository;

    @Autowired
    public WeaponServiceImpl(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public Weapon getById(Long id) {
        return weaponRepository.findById(id).get();
    }

    @Override
    public List<Weapon> getAll(Pageable pageable) {
        return weaponRepository.findAll(pageable).getContent();
    }

    @Override
    public Weapon update(Weapon object) {
        return weaponRepository.save(object);
    }

    @Override
    public Weapon save(Weapon object) {
        return weaponRepository.save(object);
    }

    @Override
    public Weapon delete(Weapon object) {
        weaponRepository.delete(object);
        return object;
    }

    @Override
    public List<WeaponsTypeDTO> findWeaponsTypeQuantity(String shipName) {
        return weaponRepository.findWeaponsTypeQuantity(shipName);
    }
}
