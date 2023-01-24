package it.unicam.ids.studenti.ll.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class Persona {
    public String nome;
    public String cognome;
    private LocalDate dataNascita = LocalDate.now();

    @JsonCreator
    public Persona(String nome, String cognome, int anno, int mese, int giorno) {
        this(nome, cognome, LocalDate.of(anno, mese, giorno));
    }

    protected Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public Persona(String nome, String cognome, LocalDate dataNascita) {
        this(nome, cognome);
        if (dataNascita.getYear() > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Non vini dal futuro, vero?");
        }
        if ((LocalDate.now().getYear() - dataNascita.getYear()) > 120) {
            throw new IllegalArgumentException("Hai piu' di 120 anni?");
        }
        if ((LocalDate.now().getYear() - dataNascita.getYear()) < 18) {
            throw new IllegalArgumentException("Hemm...chiama i tuoi genitori, grazie.");
        }
        this.dataNascita = dataNascita;
    }


    public LocalDate getDataNascita() {
        return this.dataNascita;
    }

    public short getEta() {
        LocalDate oggi = LocalDate.now(); //ho istanziato il giorno corrente per poterlo usare.
        int eta = oggi.getYear() - this.dataNascita.getYear();// qui uso per rtovare l'eta'.
        return (short) eta;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + " nato il " + dataNascita;
    }
}