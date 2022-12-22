package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

public class Dipendente extends UtenteConPrivilegi {
    protected Azienda lavoraIn;

    /**
     * Metodo di test
     * @param nome nome
     * @param cognome cognome
     */
    protected Dipendente(String nome, String cognome) {
        super(nome, cognome);
    }

    public Dipendente(String nome, String cognome, LocalDate dataNascita, Azienda azienda) {
        super(nome, cognome, dataNascita);
        assumiIn(azienda);
    }

    public Dipendente(String nome, String cognome, LocalDate dataNascita, String identificativo) {
        super(nome, cognome, dataNascita,identificativo);
    }

    public Azienda getAzienda() {
        return lavoraIn;
    }

    /**
     *
     * @param azienda non nulla
     */
    public void assumiIn(Azienda azienda) {
        if (azienda == null)
            throw new IllegalArgumentException("Un proprietario non può essere istaziato senza azienda, manca di ragione");
        azienda.addDipendente(this);
        this.lavoraIn = azienda;
    }
}