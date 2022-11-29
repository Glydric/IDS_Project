package it.unicam.ids.studenti.ll.model;

import java.util.Collection;
import java.util.Date;

interface Azienda {
    Date dataIscrizioneRegistroImprese = new Date();

    Collection<Dipendente> getListaDipendenti();

    String getRagioneSociale();

    void setRagioneSociale(String ragioneSociale);

    String getNumeroTelefono();

    void setNumeroTelefono(String numeroTelefono);

    String getEmail();

    void setEmail(String email);

    Persona getProprietario();
}