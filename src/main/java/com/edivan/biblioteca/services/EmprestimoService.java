package com.edivan.biblioteca.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edivan.biblioteca.dto.DevolucaoDTO;
import com.edivan.biblioteca.model.Bibliotecario;
import com.edivan.biblioteca.model.Emprestimo;
import com.edivan.biblioteca.model.Livro;
import com.edivan.biblioteca.model.Usuario;
import com.edivan.biblioteca.repository.BibliotecarioRepository;
import com.edivan.biblioteca.repository.EmprestimoRepository;
import com.edivan.biblioteca.repository.LivroRepository;
import com.edivan.biblioteca.repository.UsuarioRepository;

@Service
public class EmprestimoService {
	
	 @Autowired
	    private EmprestimoRepository emprestimoRepository;

	    @Autowired
	    private LivroRepository livroRepository;

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @Autowired
	    private BibliotecarioRepository bibliotecarioRepository;
	    
	  public Emprestimo registrarEmprestimo(int idUsuario, int idLivro, int idBibliotecario) {
		  
		  Livro livro = livroRepository.findById(idLivro)
				  .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
		  
		  Bibliotecario bibliotecario = bibliotecarioRepository.findById(idBibliotecario)
				  .orElseThrow(() -> new RuntimeException("Bibliotecário não encontrado!"));
		  
		  Usuario usuario = usuarioRepository.findById(idUsuario)
	                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		  
		 Emprestimo emprestimo = new Emprestimo(LocalDateTime.now(), livro, usuario, bibliotecario);
		 
		 livro.setDisponibilidade("Indisponível");
		 livroRepository.save(livro);
		 
		 return emprestimoRepository.save(emprestimo);
	  }
	  
	  public List<Emprestimo> findAll(){
		  return emprestimoRepository.findAll();
	  }
	  
	  public Emprestimo devolverEmprestimo(DevolucaoDTO devolucaoDTO) {
			Emprestimo emprestimo = emprestimoRepository.findById(devolucaoDTO.getIdEmprestimo())
					.orElseThrow(() -> new RuntimeException("Empréstimo não encontrado!"));
			
			emprestimo.setDataDevolucao(LocalDateTime.now());
			
			Livro livro = emprestimo.getLivro();
			livro.setDisponibilidade("Disponível");
			livroRepository.save(livro);
			
			return emprestimoRepository.save(emprestimo);
		}
}
