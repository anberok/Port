package com.coursework.demo.repository;

import com.coursework.demo.dto.HoldTypeDTO;
import com.coursework.demo.entity.HoldItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoldItemRepository extends PagingAndSortingRepository<HoldItem, Long> {
    @Query("SELECT NEW com.coursework.demo.dto.HoldTypeDTO(i.holdType, SUM(i.quantity)) FROM HoldItem i\n" +
           "JOIN i.ship s\n" +
           "WHERE s.name = :name\n" +
           "GROUP BY i.holdType\n")
    List<HoldTypeDTO> findHoldItemQuantity(@Param("name") String shipName);
}
