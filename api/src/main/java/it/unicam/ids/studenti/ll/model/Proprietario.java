package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;

public class Proprietario extends UtenteConPrivilegi {
    private Azienda azienda;

    /**
     * un proprietario non può essere istanziato con un'azienda nulla, ma essa può diventarlo successivamente
     *
     * @param nome        il nome del proprietario
     * @param cognome     il cognome del proprietario
     * @param dataNascita la data di nascita
     * @param azienda     l'azienda non nulla di questo proprietario
     */
    public Proprietario(String nome, String cognome, LocalDate dataNascita, Azienda azienda) {
        super(nome, cognome, dataNascita);
        setAzienda(azienda);
    }

    public Azienda getAzienda() {
        return azienda;
    }

    /**
     *
     * @param azienda non nulla
     */
    public void setAzienda(Azienda azienda) {
        if (azienda == null)
            throw new IllegalArgumentException("Un proprietario non può essere istaziato senza azienda, manca di ragione");
        azienda.proprietario = this;
        this.azienda = azienda;
    }

    /**
     * Consente di licenziare questo proprietario dalla sua carica
     */
    public void licenzia() {
        azienda.proprietario = null;
        azienda = null;
    }
}