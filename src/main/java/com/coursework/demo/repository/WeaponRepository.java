package com.coursework.demo.repository;

import com.coursework.demo.dto.WeaponsTypeDTO;
import com.coursework.demo.entity.Weapon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeaponRepository extends PagingAndSortingRepository<Weapon, Long> {
    @Query("SELECT NEW com.coursework.demo.dto.WeaponsTypeDTO(w.weaponsType, SUM(w.quantity)) FROM Weapon w\n" +
           "JOIN w.ship s\n" +
           "WHERE s.name = :name\n" +
           "GROUP BY w.weaponsType\n")
    List<WeaponsTypeDTO> findWeaponsTypeQuantity(@Param("name") String shipName);
}
