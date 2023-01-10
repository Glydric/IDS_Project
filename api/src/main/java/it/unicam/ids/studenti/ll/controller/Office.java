package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.*;

public abstract class Office {

    public UtenteIdentificabile utente;

    protected Office(Identificatore identificatore, String password) {
        this(identificatore.toString(), password);
    }

    protected Office(String identificatore, String password) {
        utente = Identificatore.getUtenteFrom(identificatore);

        if (!utente.isPasswordValid(password))
            throw new IllegalArgumentException("Login errato");

        if (utente.getAzienda() == null)
            throw new IllegalArgumentException("Non possedete alcuna azienda");
    }

    public void aggiungiDipendente(Persona persona) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        utente.getAzienda().addDipendente(persona);
    }

    public void allowDipendente(Dipendente dipendente, String permesso) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        utente.getAzienda().addPermessoDipendente(dipendente, permesso);
    }
}
