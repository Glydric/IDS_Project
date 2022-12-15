package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class UtenteConPrivilegi extends Persona {
    private final Identificatore identificativo = Identificatore.fromString(super.nome + "." + super.cognome);
    public Set<Permesso> listaPermessi = new HashSet<>();
    private String password;

    /**
     * comodity method
     *
     * @param nome    il nome del dipendente
     * @param cognome il cognome del dipendente
     */
    protected UtenteConPrivilegi(String nome, String cognome) {
        super(nome, cognome);
    }

    /**
     * un costruttore che crea un utente con ID, in cui l'ID è formato da nome.cognome
     *
     * @param nome        il nome del dipendente
     * @param cognome     il cognome del dipendente
     * @param dataNascita la data di nascita
     */
    public UtenteConPrivilegi(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }

    public UtenteConPrivilegi(String nome, String cognome, String identificativo, LocalDate dataNascita) {
        this(nome, cognome, dataNascita);
        this.identificativo.updateIdentificativo(identificativo);
    }

    public void addPermesso(Permesso permesso) {
        listaPermessi.add(permesso);
    }

    //todo check necessity
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