package it.unicam.ids.studenti.ll.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente extends Persona {
    protected final Map<Coalizione, Set<ProgrammaFedelta>> mapCoalizione
            = new HashMap<>();
    public UUID identificativoTessera;
    public boolean isFamily = false;
    private String numeroTelefono;
    private String email;
    private String password = "";

    /**
     * Un costruttore di test
     */
    protected Cliente(String nome, String cognome) {
        super(nome, cognome);
        this.identificativoTessera = UUID.randomUUID();
    }

    @JsonCreator
    public Cliente(
            String nome,
            String cognome,
            int anno,
            int mese,
            int giorno,
            String numeroTelefono,
            String email,
            Boolean isFamily) {
        this(
                nome,
                cognome,
                LocalDate.of(anno, mese, giorno),
                numeroTelefono,
                email,
                isFamily
        );
    }

    protected Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email, Boolean isFamily) {
        this(nome, cognome, dataNascita, numeroTelefono, email);
        if (isFamily != null)
            this.isFamily = isFamily;
    }

    protected Cliente(String nome, String cognome, LocalDate dataNascita, String numeroTelefono, String email) {
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

    public boolean isValid(String tessera, String password) {
        return identificativoTessera.toString().equals(tessera)
                && this.password.equals(password);
    }

    public void setPassword(String password) {
        if (password.length() <= 8)
            throw new IllegalArgumentException("Password troppo corta");

        this.password = password;
    }

    protected List<ProgrammaFedelta> getProgressAsListIn(Commerciante commerciante) {
        return getProgressIn(commerciante.getCoalizione()).stream().toList();
    }

    protected Set<ProgrammaFedelta> getProgressIn(Coalizione coalizione) {
        return mapCoalizione.get(coalizione);
    }

    /**
     * metodo di comodo
     */
    protected boolean haveProgramIn(Commerciante commerciante, ProgrammaFedelta pf) {
        return getProgressIn(commerciante.getCoalizione())
                .stream()
                .map(Object::getClass)
                .toList()
                .contains(pf.getClass());
    }


    @Override
    public String toString() {
        return "Tessera: '" + identificativoTessera + "' " + super.toString()
                + " è iscritto come " + (isFamily ? "famiglia" : "singolo");
    }
}