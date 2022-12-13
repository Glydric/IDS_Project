package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

abstract class Dipendente extends UtenteConPrivilegi {
    protected Azienda lavoraIn;

    public Dipendente(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }
}