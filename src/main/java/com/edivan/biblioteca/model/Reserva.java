package com.edivan.biblioteca.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_reserva")
	private Integer id;
	
	@Column(name ="data_reserva")
	private Date dataReserva;
	
	
	@JoinColumn(name = "id_livro", nullable = false)
	private Livro livro;
	
	@JoinColumn(name = "id_bibliotecario", nullable = false)
	private Bibliotecario bibliotecario;
	
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;
	
}
