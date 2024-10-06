package com.edivan.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edivan.biblioteca.model.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer>{

}
