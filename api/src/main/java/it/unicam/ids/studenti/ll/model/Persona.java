package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.Date;

class Persona {
    private LocalDate dataNascita;
    private String nome;
    private String cognome;

    public Persona(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }
    public String getNome(){
        return this.nome;
    }

    public LocalDate getDataNascita() {
        return this.dataNascita;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }


    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public short getEta() {

    LocalDate oggi = LocalDate.now(); //ho istanziato il giorno corrente per poterlo usare.
    int eta = oggi.getYear() - this.dataNascita.getYear();// qui uso per rtovare l'eta'.
    return (short) eta;
    }

}