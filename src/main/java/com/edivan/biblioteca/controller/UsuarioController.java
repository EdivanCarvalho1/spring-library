package com.edivan.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivan.biblioteca.model.Usuario;
import com.edivan.biblioteca.services.UsuarioService;
import com.edivan.biblioteca.token.JwtUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registerUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario novoUsuario = usuarioService.saveUsuario(usuario);
			return ResponseEntity.ok(novoUsuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/info")
	public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			String email = jwtUtil.extractUsername(token);

			Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);

			if (usuarioOptional.isPresent()) {
				Usuario usuario = usuarioOptional.get();
				return ResponseEntity.ok(usuario);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cabeçalho de autorização inválido.");
		}
	}
	 @GetMapping("/list")
	    public ResponseEntity<?> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {

	        List<Usuario> users = usuarioService.findAll();
	        
	        return ResponseEntity.ok(users);
	    }
}