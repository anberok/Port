package com.coursework.demo.repository;

import com.coursework.demo.entity.Ship;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShipRepository extends PagingAndSortingRepository<Ship, Long> {
}
