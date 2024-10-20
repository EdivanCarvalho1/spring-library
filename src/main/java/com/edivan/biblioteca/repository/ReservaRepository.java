package com.edivan.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edivan.biblioteca.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{
	
}
