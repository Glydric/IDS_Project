package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.Identificatore;
import it.unicam.ids.studenti.ll.model.Proprietario;

/**
 * La classe che definisce il backoffice ed il relativo accesso, fornisce a questo proprietario tutti i permessi
 * sulla propria azienda
 */
public class Backoffice {
    public Proprietario proprietario;

    public Backoffice(String identificatore, String password) {
        proprietario = (Proprietario) Identificatore.getUtenteFrom(identificatore);
        if (proprietario.isPasswordValid(password))
            throw new IllegalArgumentException("Login errato");
    }
}
