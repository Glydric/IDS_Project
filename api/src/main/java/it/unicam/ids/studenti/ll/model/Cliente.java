package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {
    public final int identificativoTessera;
    public final List<Commerciante> listaCommercianti = new ArrayList<>();
    public boolean isFamily = false;
    private String numeroTelefono;
    private String email;

    /**
     * Un costruttore di test
     *
     */
    protected Cliente(String nome, String cognome) {
        super(nome, cognome, LocalDate.now());
        identificativoTessera = 0;
    }

    public Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email, boolean isFamily) {
        this(nome, cognome, dataNascita, numeroTelefono, email);
        this.isFamily = isFamily;
        // todo identificativo Tessera deve essere un ID, come lo creiamo?
    }

    public Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email) {
        super(nome, cognome, dataNascita);
        setNumeroTelefono(numeroTelefono);
        setEmail(email);
        this.identificativoTessera = 0;
        // todo identificativo Tessera deve essere un ID, come lo creiamo?
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        // todo usare regex magari
        this.numeroTelefono = numeroTelefono;
    }
}