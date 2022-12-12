package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.*;

abstract class Azienda {
    public final String ragioneSociale;
    public final Map<Dipendente, Set<Permesso>> mapDipendenti = new HashMap<>();
    public LocalDate dataIscrizioneRegistroImprese = LocalDate.now();
    public Persona proprietario;
    private String numeroTelefono;
    private String email;


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
    public Azienda(String ragioneSociale, LocalDate dataIscrizione) {
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
        if (mapDipendenti.containsKey(dipendente))
            throw new IllegalArgumentException("Il dipendente è già inserito");
        mapDipendenti.put(dipendente, new HashSet<>());
    }

    public void addPermesso(Dipendente dipendente, Permesso permesso) {
        if (!mapDipendenti.containsKey(dipendente))
            addDipendente(dipendente);
        mapDipendenti.get(dipendente).add(permesso);
    }

    public List<Permesso> getPermessi(Dipendente dipendente) {
        return mapDipendenti.get(dipendente).stream().toList();
    }
}