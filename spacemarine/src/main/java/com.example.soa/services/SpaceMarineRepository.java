package com.example.soa.services;

import com.example.soa.model.SpaceMarine;
import com.example.soa.util.NameGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpaceMarineRepository extends JpaRepository<SpaceMarine, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM space_marine order by health desc LIMIT 1")
    SpaceMarine getSpaceMarineWithMaxHealth();

    @Query("SELECT new com.example.soa.util.NameGroup(s.name, count(s)) FROM SpaceMarine s GROUP BY s.name")
    List<NameGroup> groupByName();
}
