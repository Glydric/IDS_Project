package it.unicam.ids.studenti.ll.model;

import java.util.*;

abstract class Dipendente extends Persona {
	public int identificativo;
	private String password;

	public int getIdentificativo() {
		return this.identificativo;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @param password
	 */
	public boolean isPasswordValid(String password) {
		// TODO - implement Dipendente.isPasswordValid
		throw new UnsupportedOperationException();
	}

}