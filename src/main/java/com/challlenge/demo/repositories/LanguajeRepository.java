package com.challlenge.demo.repositories;

import com.challlenge.demo.entities.Languaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguajeRepository extends JpaRepository<Languaje, Long> {
}
