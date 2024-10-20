package com.edivan.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edivan.biblioteca.model.Bibliotecario;
import com.edivan.biblioteca.repository.BibliotecarioRepository;

@Service
@Qualifier("bibliotecarioService")
public class BibliotecarioService implements UserDetailsService {
	@Autowired
	private BibliotecarioRepository bibliotecarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        return bibliotecarioRepository.findByEmail(email)
	            .map(bibliotecario -> org.springframework.security.core.userdetails.User.builder()
	                .username(bibliotecario.getEmail())
	                .password(bibliotecario.getSenha())
	                .roles("ADMIN")
	                .build())
	            .orElseThrow(() -> new UsernameNotFoundException("Bibliotecário não encontrado com o email: " + email));
	    }

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
