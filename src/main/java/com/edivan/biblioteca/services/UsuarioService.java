package com.edivan.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.edivan.biblioteca.model.Usuario;
import com.edivan.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	 	@Autowired
	    private UsuarioRepository usuarioRepository;
	 	
	 	@Autowired
	 	private PasswordEncoder passwordEncoder;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Usuario usuario = usuarioRepository.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));
	        return org.springframework.security.core.userdetails.User.builder()
	            .username(usuario.getEmail())
	            .password(usuario.getSenha())
	            .roles("USER")
	            .build();
	    }

	    public Usuario saveUsuario(Usuario usuario) {
	        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
	        return usuarioRepository.save(usuario);
	    }

}
