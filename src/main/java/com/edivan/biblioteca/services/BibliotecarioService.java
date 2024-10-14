package com.edivan.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edivan.biblioteca.model.Bibliotecario;
import com.edivan.biblioteca.repository.BibliotecarioRepository;

@Service
public class BibliotecarioService {
	@Autowired
    private BibliotecarioRepository bibliotecarioRepository;
 	
 	@Autowired
 	private PasswordEncoder passwordEncoder;

    public Bibliotecario saveBibliotecario(Bibliotecario bibliotecario) {
        Optional<Bibliotecario> existingUser = bibliotecarioRepository.findByEmail(bibliotecario.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email já está em uso."); 
        }
        bibliotecario.setSenha(passwordEncoder.encode(bibliotecario.getSenha()));
        return bibliotecarioRepository.save(bibliotecario);
    }
    
    public Optional<Bibliotecario> findByEmail(String email) {
    	return bibliotecarioRepository.findByEmail(email);
    }
}
