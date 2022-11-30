package it.unicam.ids.studenti.ll.model;

import java.util.Date;

class Persona {

    final public Date dataNascita = new Date();
    public String nome;
    public String cognome;

    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    short getEta() {
        // TODO - implement Persona.getEta
        throw new UnsupportedOperationException();
    }

}