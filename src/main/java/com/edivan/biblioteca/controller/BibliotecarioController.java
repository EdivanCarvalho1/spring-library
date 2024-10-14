package com.edivan.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivan.biblioteca.model.Bibliotecario;
import com.edivan.biblioteca.services.BibliotecarioService;
import com.edivan.biblioteca.token.JwtUtil;

@RestController
@RequestMapping("/admin")
public class BibliotecarioController {
	
	@Autowired
	BibliotecarioService bibliotecarioService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/registrar")
	public ResponseEntity<?> registrarUsuario(@RequestBody Bibliotecario bibliotecario) {
		try {
			Bibliotecario novoUsuario = bibliotecarioService.saveBibliotecario(bibliotecario);
			return ResponseEntity.ok(novoUsuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/info")
	public ResponseEntity getUserInfo(@RequestHeader("Authorization") String authHeader) {
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			String email = jwtUtil.extractUsername(token);

			Optional<Bibliotecario> usuarioOptional = bibliotecarioService.findByEmail(email);

			if (usuarioOptional.isPresent()) {
				Bibliotecario bibliotecario = usuarioOptional.get();
				return ResponseEntity.ok(bibliotecario);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cabeçalho de autorização inválido.");
		}
	}
}
