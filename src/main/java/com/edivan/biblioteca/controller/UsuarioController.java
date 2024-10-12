package com.edivan.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivan.biblioteca.model.Usuario;
import com.edivan.biblioteca.services.UsuarioService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {
	
	@Autowired
	UsuarioService userService;
	
	 @Autowired
	    private UsuarioService usuarioService;

	    @PostMapping("/registrar")
	    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
	        Usuario novoUsuario = usuarioService.saveUsuario(usuario);
	        return ResponseEntity.ok(novoUsuario);
	    }

}
