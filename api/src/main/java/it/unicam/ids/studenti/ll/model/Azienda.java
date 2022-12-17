package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

abstract class Azienda {
    public final String ragioneSociale;
    public final Set<Dipendente> mapDipendenti = new HashSet<>();
    public LocalDate dataIscrizioneRegistroImprese = LocalDate.now();
    /**
     * Il proprietario può essere null, benchè nel mondo reale non ha senso come elemento
     * nel nostro modello il proprietario è si chi possiede l'azienda ma è soprattutto il suo admin
     * ed il nostro interesse è proprio nel fatto che lui può controllare tutti gli aspetti dell'azienda senza limiti
     */
    protected Proprietario proprietario;
    private String numeroTelefono;
    private String email;


    /**
     * @param ragioneSociale il nome dell'azienda
     */
    protected Azienda(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * @param ragioneSociale il nome dell'azienda
     * @param dataIscrizione la data d'iscrizione al registro imprese
     */
    public Azienda(String ragioneSociale, LocalDate dataIscrizione) {
        this(ragioneSociale);
        setDate(dataIscrizione);
    }

    public Azienda(String ragioneSociale, LocalDate dataIscrizione, Proprietario proprietario) {
        this(ragioneSociale, dataIscrizione);
        setProprietario(proprietario);
    }

    /**
     * Rimuove quest'azienda dal vecchio proprietario e la aggiunge al nuovo.
     * Il proprietario può essere nullo ma solo alla creazione e quando il proprietario viene licenziato,
     * in questo metodo non ha senso impostare un proprietario come null, piuttosto definirlo come licenziato dal metodo
     * apposito Proprietario.licenzia()
     *
     * @param newProprietario il nuovo proprietario da aggiungere a quest'azienda
     */
    public void setProprietario(Proprietario newProprietario) {
        if (newProprietario == null) {
            throw new IllegalArgumentException("L'azienda deve avere un proprietario, non sono accettati valori null");
        }
        this.proprietario.licenzia();
        this.proprietario = newProprietario;
        newProprietario.setAzienda(this);
    }

    /**
     * @param dataIscrizione la data d'iscrizione al registro imprese
     */
    private void setDate(LocalDate dataIscrizione) {
        if (dataIscrizione.isAfter(LocalDate.now())) return;

        this.dataIscrizioneRegistroImprese = dataIscrizione;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        // todo controllo del numero
// Possiamo fare una regex che accetta solo numeri, spazi, e i segni +- perchè
// potresti utilizzare il trattino per separare il numero, e il + serve per il prefisso.
        // inoltre non possiamo avere la certezza che il numero è corretto se non facciamo sms validation

        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // todo controllo del email
        this.email = email;
    }

    public void addDipendente(Dipendente dipendente) {
        if (mapDipendenti.contains(dipendente))
            throw new IllegalArgumentException("Il dipendente è già inserito");
        mapDipendenti.add(dipendente);
        dipendente.lavoraIn = this;
    }
}