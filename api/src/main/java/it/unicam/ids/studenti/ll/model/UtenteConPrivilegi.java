package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.Objects;

public abstract class UtenteConPrivilegi extends Persona {
    private final Identificatore identificativo;
    private String password;

    public UtenteConPrivilegi(String nome, String cognome, String identificativo, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
        this.identificativo = Identificatore.fromString(identificativo);
    }

    public UtenteConPrivilegi(String nome, String cognome, LocalDate dataNascita) {
        this(nome, cognome, nome + "." + cognome, dataNascita);
    }

    public String getIdentificativo() {
        return identificativo.toString();
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