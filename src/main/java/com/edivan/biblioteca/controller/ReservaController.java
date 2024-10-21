package com.edivan.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivan.biblioteca.dto.EmprestimoDTO;
import com.edivan.biblioteca.model.Emprestimo;
import com.edivan.biblioteca.model.Reserva;
import com.edivan.biblioteca.services.ReservaService;
import com.edivan.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/reserva")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	ReservaService reservaService;
	
	
	@PostMapping("/reservar")
	public ResponseEntity<Reserva> criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {

	    if (!usuarioService.verificarSenha(emprestimoDTO.getIdUsuario(), emprestimoDTO.getSenha())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    Reserva novoEmprestimo = reservaService.registrarReserva(
	        emprestimoDTO.getIdUsuario(),
	        emprestimoDTO.getIdLivro(),
	        emprestimoDTO.getIdBibliotecario()
	    );
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoEmprestimo);
	}
}
