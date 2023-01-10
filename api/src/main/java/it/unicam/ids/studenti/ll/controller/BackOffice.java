package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.Identificatore;

/**
 * La classe che definisce il backoffice ed il relativo accesso, fornisce a questo proprietario tutti i permessi
 * sulla propria azienda
 */
public class BackOffice extends Office {

    public BackOffice(Identificatore identificatore, String password) {
        super(identificatore, password);
    }


}
