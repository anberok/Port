package com.coursework.demo.service.impl;

import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.entity.HoldItem;
import com.coursework.demo.repository.HoldItemRepository;
import com.coursework.demo.service.HoldItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class HoldItemServiceImpl implements HoldItemService {

    private HoldItemRepository holdItemRepository;

    @Autowired
    public HoldItemServiceImpl(HoldItemRepository holdItemRepository) {
        this.holdItemRepository = holdItemRepository;
    }

    @Override
    public HoldItem getById(Long id) {
        return holdItemRepository.findById(id).get();
    }

    @Override
    public List<HoldItem> getAll(Pageable pageable) {
        return holdItemRepository.findAll(pageable).getContent();
    }

    @Override
    public HoldItem save(HoldItem object) {
        return holdItemRepository.save(object);
    }

    @Override
    public HoldItem delete(HoldItem object) {
        holdItemRepository.delete(object);
        return object;
    }

    @Override
    public List<HoldTypeDTO> findHoldItemQuantity(String shipName) {
        return holdItemRepository.findHoldItemQuantity(shipName);
    }
}
