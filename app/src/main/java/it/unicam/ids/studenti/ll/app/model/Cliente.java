package it.unicam.ids.studenti.ll.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente extends Persona {
    public UUID identificativoTessera;
    public Set<Coalizione> listaCommercianti = new HashSet<>();
    public boolean isFamily = false;
    private String numeroTelefono;
    private String email;
    private String password;

    /**
     * Un costruttore di test
     */
    protected Cliente(String nome, String cognome) {
        super(nome, cognome);
        this.identificativoTessera = UUID.randomUUID();
    }

    public Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email, Boolean isFamily) {
        this(nome, cognome, dataNascita, numeroTelefono, email);
        if (isFamily != null)
            this.isFamily = isFamily;
    }

    Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email) {
        super(nome, cognome, dataNascita);
        setNumeroTelefono(numeroTelefono);
        setEmail(email);
        this.identificativoTessera = UUID.randomUUID();
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
        Pattern numTelx = Pattern.compile("[0-9]{10}");
        Matcher controllore = numTelx.matcher(numeroTelefono);
        if (!controllore.find()) {
            throw new IllegalArgumentException("Numero non valida");
        }
        this.numeroTelefono = numeroTelefono;
    }
}