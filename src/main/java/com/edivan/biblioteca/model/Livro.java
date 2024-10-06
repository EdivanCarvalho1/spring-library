package com.edivan.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "livro")
public class Livro implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_livro")
	private Integer id;
	
	private String titulo;
	
	@Column(name = "ano_publicacao", nullable = false)
	private LocalDate ano;
	
	private String genero;
	@Column(name = "img_src", nullable = false)
	private String img;
	private String isbn;
	private String disponibilidade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_autor", nullable = false)
	private Autor autor;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_editora", nullable = false)
	private Editora editora;
	
}
