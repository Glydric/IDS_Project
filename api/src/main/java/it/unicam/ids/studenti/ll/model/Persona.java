package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

class Persona {
    public String nome;
    public String cognome;
    private LocalDate dataNascita = LocalDate.now();

    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public Persona(String nome, String cognome, LocalDate dataNascita) {
        this(nome, cognome);
        if (dataNascita.getYear() > LocalDate.now().getYear()){
            throw new IllegalArgumentException("Non vini dal futuro, vero?");
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

}