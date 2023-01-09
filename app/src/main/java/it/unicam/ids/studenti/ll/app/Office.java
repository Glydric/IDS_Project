package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Identificatore;
import it.unicam.ids.studenti.ll.model.UtenteConPrivilegi;

abstract class Office {
    public UtenteConPrivilegi utente;

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
