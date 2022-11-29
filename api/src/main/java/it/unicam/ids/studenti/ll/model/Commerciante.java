package it.unicam.ids.studenti.ll.model;

import java.util.*;

public class Commerciante implements Azienda {

	Collection<Prodotto> listaProdotti;
	/**
	 * 
	 * @param ragioneSociale
	 * @param dataIscrizione
	 */
	public Commerciante(String ragioneSociale, Date dataIscrizione) {
		// TODO - implement Commerciante.Commerciante
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dataIscrizione
	 */
	public Commerciante(Date dataIscrizione) {
		// TODO - implement Commerciante.Commerciante
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<Dipendente> getListaDipendenti() {
		return null;
	}

	@Override
	public String getRagioneSociale() {
		return null;
	}

	@Override
	public void setRagioneSociale(String ragioneSociale) {

	}

	@Override
	public String getNumeroTelefono() {
		return null;
	}

	@Override
	public void setNumeroTelefono(String numeroTelefono) {

	}

	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public void setEmail(String email) {

	}

	@Override
	public Persona getProprietario() {
		return null;
	}

}