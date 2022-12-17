package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//TODO rename to UtenteIdentificabile
public abstract class UtenteConPrivilegi extends Persona {
    public final Identificatore identificativo = Identificatore.fromUtente(this);
    public Set<Permesso> listaPermessi = new HashSet<>();
    private String password;

    /**
     * commodity method
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

    public UtenteConPrivilegi(String nome, String cognome, LocalDate dataNascita, String identificativo) {
        this(nome, cognome, dataNascita);
        this.identificativo.updateIdentificativo(identificativo);
    }

    public void addPermesso(Permesso permesso) {
        listaPermessi.add(permesso);
    }

    public void setPassword(String password) throws IllegalArgumentException {
        if(password.contains(" "))
            throw new IllegalArgumentException("La password non può avere spazi");
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