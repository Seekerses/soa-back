package com.example.soa.service;

import com.example.soa.model.dto.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipRepository extends JpaRepository<Starship, Long> {
}
