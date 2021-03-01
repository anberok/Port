package com.coursework.demo.repository;

import com.coursework.demo.entity.Crew;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CrewRepository extends PagingAndSortingRepository<Crew, Long> {
}
