package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

public class Proprietario extends UtenteConPrivilegi {
    protected Azienda azienda;

    public Proprietario(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }

    public Azienda getAzienda() {
        return azienda;
    }
}