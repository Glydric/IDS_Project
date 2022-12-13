package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class UtenteConPrivilegi extends Persona {
    protected String identificativo;
    private String password;

    public UtenteConPrivilegi(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
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