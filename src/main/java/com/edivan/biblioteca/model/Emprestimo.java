package com.edivan.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Emprestimo implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id_emprestimo")
	    private Integer idEmprestimo;

	    @Column(name = "data_emprestimo")
	    private LocalDateTime dataEmprestimo;

	    @Column(name = "data_devolucao")
	    private LocalDateTime dataDevolucao;

	    @ManyToOne
	    @JoinColumn(name = "id_livro")
	    private Livro livro;

	    @ManyToOne
	    @JoinColumn(name = "id_usuario")
	    private Usuario usuario;

	    @ManyToOne
	    @JoinColumn(name = "id_bibliotecario")
	    private Bibliotecario bibliotecario;
	 
	 public Emprestimo(LocalDateTime dataEmprestimo, Livro livro, Usuario usuario, Bibliotecario bibliotecario) {
		 this.dataEmprestimo = dataEmprestimo;
		 this.dataDevolucao = null;
		 this.livro = livro;
		 this.usuario = usuario;
		 this.bibliotecario = bibliotecario;
	 }
}
