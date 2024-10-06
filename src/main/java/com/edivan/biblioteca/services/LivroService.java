package com.edivan.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivan.biblioteca.model.Livro;
import com.edivan.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	LivroRepository livroRepo;
	
	public List<Livro> findAll(){
		return livroRepo.findAll();
	}
	
	public List<Livro> searchByTitle(String titulo){
		return livroRepo.searchByTitulo(titulo);
	}
}
