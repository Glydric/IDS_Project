package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.Identificatore;

/**
 * La classe che definisce il frontoffice ed il relativo accesso di un dipendente, fornisce a questo dipendente i permessi
 * concordati con il proprietario sulla relativa azienda
 */
public class FrontOffice extends Office {

    public FrontOffice(Identificatore identificatore, String password) {
        super(identificatore, password);

    }
}
