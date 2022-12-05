package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

abstract class Dipendente extends Persona {
    public int identificativo;
    private String password;
	public Azienda lavoraIn;

    public Dipendente(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome,dataNascita);
    }

    public int getIdentificativo() {
        return this.identificativo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param password
     */
    public boolean isPasswordValid(String password) {
        // TODO - implement Dipendente.isPasswordValid
        return password == this.password;
//        throw new UnsupportedOperationException(); ????????????
        
    }

}