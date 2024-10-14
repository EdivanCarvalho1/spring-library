package com.edivan.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edivan.biblioteca.model.Livro;
import com.edivan.biblioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
@CrossOrigin(origins = "http://localhost:3000")
public class LivroController {
	
	@Autowired
	LivroService livroService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Livro>>findAll(){
		List<Livro> livro = livroService.findAll(); 
		if(livro.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(livro);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Livro>>searchByTitle(@RequestParam String title){
		List<Livro> livroTitle = livroService.searchByTitle(title);
		
		if(livroTitle.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(livroTitle);
	}
}
