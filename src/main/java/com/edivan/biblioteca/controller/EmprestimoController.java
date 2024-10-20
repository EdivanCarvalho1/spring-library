package com.edivan.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edivan.biblioteca.dto.DevolucaoDTO;
import com.edivan.biblioteca.dto.EmprestimoDTO;
import com.edivan.biblioteca.model.Emprestimo;
import com.edivan.biblioteca.services.EmprestimoService;
import com.edivan.biblioteca.services.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/emprestimo")
public class EmprestimoController {
	
	@Autowired
	EmprestimoService emprestimoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Emprestimo>> findAll(){
		List<Emprestimo> listaEmprestimo = emprestimoService.findAll();
		if(listaEmprestimo.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listaEmprestimo);
		}
		return ResponseEntity.ok(listaEmprestimo);
	}
	@PostMapping("/emprestar")
	public ResponseEntity<Emprestimo> criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {

	    if (!usuarioService.verificarSenha(emprestimoDTO.getIdUsuario(), emprestimoDTO.getSenha())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }

	    Emprestimo novoEmprestimo = emprestimoService.registrarEmprestimo(
	        emprestimoDTO.getIdUsuario(),
	        emprestimoDTO.getIdLivro(),
	        emprestimoDTO.getIdBibliotecario()
	    );
	    return ResponseEntity.status(HttpStatus.CREATED).body(novoEmprestimo);
	}
	
	@PostMapping("/devolver")
	public ResponseEntity<Emprestimo> devolverEmprestimo(@RequestBody DevolucaoDTO devolucaoDTO) {
		Emprestimo emprestimoDevolvido = emprestimoService.devolverEmprestimo(devolucaoDTO);
		return ResponseEntity.ok(emprestimoDevolvido);
	}
}
