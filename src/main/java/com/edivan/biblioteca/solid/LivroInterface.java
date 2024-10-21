package com.edivan.biblioteca.solid;

import java.util.List;

import com.edivan.biblioteca.model.Livro;

public interface LivroInterface {
	public List<Livro> searchByTitle(String titulo);
	public List<Livro> findAll();
}
