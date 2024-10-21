package com.edivan.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edivan.biblioteca.model.Usuario;
import com.edivan.biblioteca.repository.UsuarioRepository;
import com.edivan.biblioteca.solid.UsuarioInterface;

@Service
@Qualifier("usuarioService")
public class UsuarioService implements UserDetailsService, UsuarioInterface {
	
	 	@Autowired
	    private UsuarioRepository usuarioRepository;
	 	
	 	@Autowired
	 	private PasswordEncoder passwordEncoder;

	 	@Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        return usuarioRepository.findByEmail(email)
	            .map(usuario -> org.springframework.security.core.userdetails.User.builder()
	                .username(usuario.getEmail())
	                .password(usuario.getSenha())
	                .roles("USER")
	                .build())
	            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));
	    }
	    
	    public List<Usuario> findAll() {
	    	return usuarioRepository.findAll();
	    }
	    public Usuario saveUsuario(Usuario usuario) {
	        Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuario.getEmail());

	        if (existingUser.isPresent()) {
	            throw new RuntimeException("Email já está em uso."); 
	        }
	        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
	        return usuarioRepository.save(usuario);
	    }
	    
	    public Optional<Usuario> findByEmail(String email) {
	    	return usuarioRepository.findByEmail(email);
	    }
	    public boolean verificarSenha(int idUsuario, String senha) {
	        Usuario usuario = usuarioRepository.findById(idUsuario)
	            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

	        return passwordEncoder.matches(senha, usuario.getSenha());
	    }
}
