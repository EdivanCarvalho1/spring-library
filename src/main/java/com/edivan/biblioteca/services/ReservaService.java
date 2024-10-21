package com.edivan.biblioteca.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivan.biblioteca.model.Bibliotecario;
import com.edivan.biblioteca.model.Livro;
import com.edivan.biblioteca.model.Reserva;
import com.edivan.biblioteca.model.Usuario;
import com.edivan.biblioteca.repository.BibliotecarioRepository;
import com.edivan.biblioteca.repository.LivroRepository;
import com.edivan.biblioteca.repository.ReservaRepository;
import com.edivan.biblioteca.repository.UsuarioRepository;

@Service
public class ReservaService {
	
	@Autowired
	ReservaRepository reservaRepository;
	
	@Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;
	
	public Reserva registrarReserva(int idUsuario, int idLivro, int idBibliotecario) {
		  
		  Livro livro = livroRepository.findById(idLivro)
				  .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
		  
		  Bibliotecario bibliotecario = bibliotecarioRepository.findById(idBibliotecario)
				  .orElseThrow(() -> new RuntimeException("Bibliotecário não encontrado!"));
		  
		  Usuario usuario = usuarioRepository.findById(idUsuario)
	                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		  
		 Reserva reserva = new Reserva(LocalDateTime.now(), livro, bibliotecario, usuario);
		 
		 
		 return reservaRepository.save(reserva);
	  }
}
