package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Dipendente;
import it.unicam.ids.studenti.ll.model.Identificatore;

/**
 * La classe che definisce il frontoffice ed il relativo accesso di un dipendente, fornisce a questo dipendente i permessi
 * concordati con il proprietario sulla relativa azienda
 */
public class Frontoffice extends Office {

    Frontoffice(Identificatore identificatore, String password) {
        this(identificatore.toString(), password);
    }

    Frontoffice(String identificatore, String password) {
        super(identificatore, password);

        if (((Dipendente) super.utente).getAzienda() == null)
            throw new IllegalArgumentException("Non lavorate in alcuna azienda");
    }

    /**
     *
     * @return il commerciante in cui si lavora
     */
    @Override
    public Commerciante getCommerciante() {
        return (Commerciante) ((Dipendente) super.utente).getAzienda();
    }
}
