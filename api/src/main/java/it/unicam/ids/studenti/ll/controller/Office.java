package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Identificatore;
import it.unicam.ids.studenti.ll.model.UtenteIdentificabile;

public abstract class Office {
    public UtenteIdentificabile utente;

    Office(Identificatore identificatore, String password) {
        this(identificatore.toString(), password);
    }

    Office(String identificatore, String password) {
        utente = Identificatore.getUtenteFrom(identificatore);

        if (!utente.isPasswordValid(password))
            throw new IllegalArgumentException("Login errato");
    }

    /**
     * @return il relativo commerciante
     */
    abstract Commerciante getCommerciante();
}
