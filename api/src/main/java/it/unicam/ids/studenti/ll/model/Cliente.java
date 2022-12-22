package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente extends Persona {
    public int identificativoTessera; //String è meglio secondo me, perchè Integer è grande 10 cifre (2147483647) e serve per farci i calcoli.
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
        identificativoTessera = 0;
    }

    public Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email, boolean isFamily) {
        this(nome, cognome, dataNascita, numeroTelefono, email);
        this.isFamily = isFamily;
        // todo identificativo Tessera deve essere un ID, come lo creiamo?
        //  random UUID?
    }

    public Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email) {
        super(nome, cognome, dataNascita);
        setNumeroTelefono(numeroTelefono);
        setEmail(email);
        this.identificativoTessera = 0;
        // todo identificativo Tessera deve essere un ID, come lo creiamo?
        //  random UUID?
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
        // todo fatto
        Pattern numTelx = Pattern.compile("[0-9]{10}");
        Matcher controllore = numTelx.matcher(numeroTelefono);
        if (!controllore.find()){
            throw new IllegalArgumentException("Numero non valida");
        }
        this.numeroTelefono = numeroTelefono;
    }
}