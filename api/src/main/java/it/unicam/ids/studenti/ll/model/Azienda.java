package it.unicam.ids.studenti.ll.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract class Azienda{
    public final String ragioneSociale;
    public Date dataIscrizioneRegistroImprese = new Date();
    private String numeroTelefono;
    private String email;

    public Persona proprietario;
    public List<Dipendente> listaDipendenti = new ArrayList<>();


    /**
     * @param ragioneSociale
     */
    public Azienda(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    /**
     * @param ragioneSociale
     * @param dataIscrizione
     */
    public Azienda(String ragioneSociale, Date dataIscrizione) {
        this(ragioneSociale);
        setDate(dataIscrizione);
    }

    public Azienda(String ragioneSociale, Persona proprietario) {
        this(ragioneSociale);
        this.proprietario = proprietario;
    }

    /**
     * @param dataIscrizione
     */
    private void setDate(Date dataIscrizione) {
        if (dataIscrizione.after(new Date())) return;

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
}