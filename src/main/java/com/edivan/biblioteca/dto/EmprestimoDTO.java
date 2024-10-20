package com.edivan.biblioteca.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmprestimoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idLivro;
    private int idUsuario;
    private int idBibliotecario;
    private String senha;
}
