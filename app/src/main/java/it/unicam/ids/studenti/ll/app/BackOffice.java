package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Identificatore;
import it.unicam.ids.studenti.ll.model.Proprietario;

/**
 * La classe che definisce il backoffice ed il relativo accesso, fornisce a questo proprietario tutti i permessi
 * sulla propria azienda
 */
public class BackOffice extends Office {
    public BackOffice(Identificatore identificatore, String password) {
        this(identificatore.toString(), password);
    }

    public BackOffice(String identificatore, String password) {
        super(identificatore, password);

        if (((Proprietario) super.utente).getAzienda() == null)
            throw new IllegalArgumentException("Non possedete alcuna azienda");
    }

    /**
     * @return il commerciante di cui si è proprietari
     */
    @Override
    public Commerciante getCommerciante() {
        return (Commerciante) ((Proprietario) super.utente).getAzienda();
    }
}
