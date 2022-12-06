package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

public class Tecnico extends Dipendente {
    public Tecnico(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }
}