package it.unicam.ids.studenti.ll.app.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Identificatore {
    private static final Map<String, UtenteIdentificabile> listaID = new HashMap<>();
    private String identificativo;

    private Identificatore(UtenteIdentificabile utente) {
        String id = getFormat(utente);
        setIdentificativo(id);
        listaID.put(id, utente);
    }

    public static String getFormat(String nome, String cognome) {
        return (nome + '.' + cognome).toLowerCase();

    }

    public static String getFormat(UtenteIdentificabile utente) {
        return getFormat(utente.nome, utente.cognome);
    }


    public static Identificatore fromUtente(UtenteIdentificabile utente) {
        return new Identificatore(utente);
    }

    public static UtenteIdentificabile getUtenteFrom(String identificativo) {
        return listaID.get(identificativo.toLowerCase());
    }

    public static boolean isAvailable(String identificativo) {
        return !listaID.containsKey(identificativo);
    }

    private void setIdentificativo(String identificativo) {
        if (!isAvailable(identificativo))
            throw new IllegalArgumentException("Identificativo già presente nel sistema");
        if (identificativo.contains(" "))
            throw new IllegalArgumentException("Non è possibile inserire spazi nell'ID");
        this.identificativo = identificativo.toLowerCase();
    }

    /**
     * Usato per aggiornare l'identificativo corrente
     *
     * @param identificativo il nuovo id per questo identificatore
     */
    public void updateIdentificativo(String identificativo) {
        setIdentificativo(identificativo);
        listaID.replace(identificativo, listaID.get(this.identificativo));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Identificatore that)) return false;

        return Objects.equals(identificativo, that.identificativo);
    }

    @Override
    public int hashCode() {
        return identificativo != null ? identificativo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return identificativo;
    }
}