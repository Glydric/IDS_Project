package it.unicam.ids.studenti.ll.app.model;

import java.time.LocalDate;

public class Dipendente extends UtenteIdentificabile {
    private Azienda lavoraIn;

    /**
     * Metodo di test
     *
     * @param nome    nome
     * @param cognome cognome
     */
    protected Dipendente(String nome, String cognome) {
        super(nome, cognome);
    }

    protected Dipendente(String nome, String cognome, LocalDate dataNascita, Azienda azienda) {
        super(nome, cognome, dataNascita);
        assumiIn(azienda);
    }

    protected static Dipendente fromPersona(Persona persona, Azienda azienda) {
        return new Dipendente(
                persona.nome,
                persona.cognome,
                persona.getDataNascita(),
                azienda
        );
    }

    public Azienda getAzienda() {
        return lavoraIn;
    }

    /**
     * @param azienda non nulla
     */
    private void assumiIn(Azienda azienda) {
        if (azienda == null)
            throw new IllegalArgumentException("Un proprietario non può essere instaziato senza azienda, manca di ragione");
        this.lavoraIn = azienda;
    }

}