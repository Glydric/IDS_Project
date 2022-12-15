package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

public class Proprietario extends UtenteConPrivilegi {
    public Azienda azienda;

    public Proprietario(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }
}