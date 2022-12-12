package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

abstract class Dipendente extends Persona {
    public int identificativo;
    public Azienda lavoraIn;
    private String password;

    public Dipendente(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }

    public int getIdentificativo() {
        return this.identificativo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param password la password da controllare
     */
    public boolean isPasswordValid(String password) {
        // TODO - test Dipendente.isPasswordValid
        return Objects.equals(password, this.password);
//        throw new UnsupportedOperationException(); ????????????

    }

}