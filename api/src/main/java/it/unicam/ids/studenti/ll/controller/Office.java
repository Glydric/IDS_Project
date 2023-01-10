package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.AuthorizationException;
import it.unicam.ids.studenti.ll.model.Dipendente;
import it.unicam.ids.studenti.ll.model.Identificatore;
import it.unicam.ids.studenti.ll.model.UtenteIdentificabile;

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


    private String getActualMethodName() {
        return new Throwable()
                .getStackTrace()[1]
                .getMethodName();
    }

    public void aggiungiDipendente(Dipendente dipendente) throws AuthorizationException {

        String nameofCurrMethod = getActualMethodName();
        System.out.println(nameofCurrMethod);

        if (!utente.listaPermessi.contains(getActualMethodName()))
            throw new AuthorizationException("L'utente non ha i permessi");
        utente.getAzienda().addDipendente(dipendente);
    }
}
