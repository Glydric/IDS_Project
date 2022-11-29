package it.unicam.ids.studenti.ll.model;

import java.util.*;

public class Cliente extends Persona {
	private int identificativoTessera;
	public String numeroTelefono;
	private String email;
	public boolean isFamily = false;

	public int getIdentificativoTessera() {
		return this.identificativoTessera;
	}

	public void setIdentificativoTessera(int identificativoTessera) {
		this.identificativoTessera = identificativoTessera;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}