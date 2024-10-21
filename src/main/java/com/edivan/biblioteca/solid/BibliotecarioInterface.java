package com.edivan.biblioteca.solid;

import java.util.Optional;

import com.edivan.biblioteca.model.Bibliotecario;

public interface BibliotecarioInterface {
	public Optional<Bibliotecario> findByEmail(String email);
	public Bibliotecario saveBibliotecario(Bibliotecario bibliotecario);
}
