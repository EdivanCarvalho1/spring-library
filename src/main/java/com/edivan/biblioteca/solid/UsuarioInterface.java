package com.edivan.biblioteca.solid;

import java.util.List;
import java.util.Optional;

import com.edivan.biblioteca.model.Usuario;

public interface UsuarioInterface {
	public boolean verificarSenha(int idUsuario, String senha);
	public Optional<Usuario> findByEmail(String email);
	public Usuario saveUsuario(Usuario usuario);
	public List<Usuario> findAll();
}
