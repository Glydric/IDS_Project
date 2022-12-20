package it.unicam.ids.studenti.ll.model;

import javax.validation.constraints.Email; //aggiunta librerie per la convalidazione dell'email
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*abstract*/ public class Azienda {
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
        // todo fatto
        Pattern numTelx = Pattern.compile("[0-9]{10}");
        Matcher controllore = numTelx.matcher(numeroTelefono);
        if (!controllore.find()){
            throw new IllegalArgumentException("Numero non valida.");
        }
        this.numeroTelefono = numeroTelefono;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        // todo fatto
        Pattern emailx = Pattern.compile(".*@.*");
        Matcher controllore = emailx.matcher(email);
        if (!controllore.find()){
            throw new IllegalArgumentException("Email non valida");
        }
        this.email = email;
    }

    public void addDipendente(Dipendente dipendente) {
        if (mapDipendenti.contains(dipendente))
            throw new IllegalArgumentException("Il dipendente è già inserito");
        mapDipendenti.add(dipendente);
    }
}
