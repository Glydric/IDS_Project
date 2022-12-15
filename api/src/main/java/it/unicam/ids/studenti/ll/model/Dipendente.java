package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

class Dipendente extends UtenteConPrivilegi {
    protected Azienda lavoraIn;

    protected Dipendente(String nome, String cognome) {
        super(nome, cognome);
    }

    public Dipendente(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }

    public Dipendente(String nome, String cognome, String identificativo, LocalDate dataNascita) {
        super(nome, cognome, identificativo, dataNascita);
    }
}