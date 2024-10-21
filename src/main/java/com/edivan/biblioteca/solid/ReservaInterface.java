package com.edivan.biblioteca.solid;

import com.edivan.biblioteca.model.Reserva;

public interface ReservaInterface {
	
	public Reserva registrarReserva(int idUsuario, int idLivro, int idBibliotecario);
}
