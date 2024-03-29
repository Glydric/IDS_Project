package it.unicam.ids.studenti.ll.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class UtenteIdentificabile extends Persona {
    public final Identificatore identificativo = Identificatore.fromUtente(this);
    protected final Set<String> listaPermessi = new HashSet<>();
    private String password = "";


    /**
     * commodity method
     *
     * @param nome    il nome del dipendente
     * @param cognome il cognome del dipendente
     */
    protected UtenteIdentificabile(String nome, String cognome) {
        super(nome, cognome);
    }

    /**
     * un costruttore che crea un utente con ID, in cui l'ID è formato da nome.cognome
     *
     * @param nome        il nome del dipendente
     * @param cognome     il cognome del dipendente
     * @param dataNascita la data di nascita
     */
    public UtenteIdentificabile(String nome, String cognome, LocalDate dataNascita) {
        super(nome, cognome, dataNascita);
    }

    public UtenteIdentificabile(String nome, String cognome, LocalDate dataNascita, String identificativo) {
        this(nome, cognome, dataNascita);
        this.identificativo.updateIdentificativo(identificativo);
    }

    protected String getPreviousMethodOf(int i) {
        return new Throwable()
                .getStackTrace()[i]
                .getMethodName();
    }

    public void authorize() throws AuthorizationException {
        if (!(listaPermessi.contains(getPreviousMethodOf(2))
                || listaPermessi.contains("ALL")
                || listaPermessi.contains("all")))
            throw new AuthorizationException("L'utente non ha i permessi");
    }

    /**
     * @return l'azienda in cui questo utente può identificarsi
     */
    public abstract Azienda getAzienda();

    public void setPassword(String password) throws IllegalArgumentException {
        if (password.contains(" "))
            throw new IllegalArgumentException("La password non può avere spazi");
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password troppo corta, non è sicura.");
        }
        this.password = password;
    }

    /**
     * @param password la password da controllare
     */
    public boolean isPasswordValid(String password) {
        return Objects.equals(password, this.password);
    }

    protected void addPermessi(String... permessi) {
        addPermessi(List.of(permessi));
    }

    protected void addPermessi(List<String> permessi) {
        listaPermessi.addAll(permessi);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UtenteIdentificabile that)) return false;

        return Objects.equals(identificativo, that.identificativo);
    }

    @Override
    public int hashCode() {
        return identificativo.hashCode();
    }


}