package it.unicam.ids.studenti.ll.model;

import java.util.Date;

class Persona {

	private Date dataNascita;
	public String nome;
	private String cognome;

	public Date getDataNascita() {
		return this.dataNascita;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	short getEta() {
		// TODO - implement Persona.getEta
		throw new UnsupportedOperationException();
	}

}