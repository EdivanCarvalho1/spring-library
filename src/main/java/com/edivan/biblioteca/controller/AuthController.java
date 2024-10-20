package com.edivan.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivan.biblioteca.model.AuthRequest;
import com.edivan.biblioteca.model.AuthResponse;
import com.edivan.biblioteca.model.Bibliotecario;
import com.edivan.biblioteca.model.Usuario;
import com.edivan.biblioteca.services.BibliotecarioService;
import com.edivan.biblioteca.services.UsuarioService;
import com.edivan.biblioteca.token.JwtUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private BibliotecarioService bibliotecarioService;
	
	@Autowired
	private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getSenha())
            );

            String role = getUserRole(authRequest.getEmail());

            String token = jwtUtil.generateToken(authRequest.getEmail(), role);
            return ResponseEntity.ok(new AuthResponse(token, role));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    private String getUserRole(String email) {
        Optional<Bibliotecario> bibliotecarioOptional = bibliotecarioService.findByEmail(email);
        if (bibliotecarioOptional.isPresent()) {
            return bibliotecarioOptional.get().getRole();
        }

        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(email);
        return usuarioOptional.map(Usuario::getRole).orElse("user");
    }
}
