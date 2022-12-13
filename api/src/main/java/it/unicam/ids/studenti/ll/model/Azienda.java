package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.*;

abstract class Azienda {
    public final String ragioneSociale;
    public final Set<Dipendente> mapDipendenti = new HashSet<>();
    public LocalDate dataIscrizioneRegistroImprese = LocalDate.now();
    public Persona proprietario;
    private String numeroTelefono;
    private String email;


    /**
     * @param ragioneSociale il nome dell'azienda
     */
    public Azienda(String ragioneSociale) {
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

    public Azienda(String ragioneSociale, Persona proprietario) {
        this(ragioneSociale);
        this.proprietario = proprietario;
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
    }
}