package com.edivan.biblioteca.solid;

import java.util.List;

import com.edivan.biblioteca.dto.DevolucaoDTO;
import com.edivan.biblioteca.model.Emprestimo;

public interface EmprestimoInterface {
	
	 public Emprestimo registrarEmprestimo(int idUsuario, int idLivro, int idBibliotecario);
	 public List<Emprestimo> findAll();
	 public Emprestimo devolverEmprestimo(DevolucaoDTO devolucaoDTO);
	 
}
