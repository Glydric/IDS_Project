package it.unicam.ids.studenti.ll.model;

import java.util.HashSet;
import java.util.Set;

public class Identificatore {
    private static final Set<String> listaID = new HashSet<>();
    private String identificativo;

    private Identificatore(String identificativo) {
        setIdentificativo(identificativo);
        listaID.add(identificativo);
    }

    /**
     * factory method to create an identificatore using a string
     *
     * @return L'identificatore creato
     */
    public static Identificatore fromString(String identificativo) {
        return new Identificatore(identificativo);
        // TODO - test Identificatore.createIdentificatoreFromString
    }

    public static boolean isAvailable(String identificativo) {
        return !listaID.contains(identificativo);
    }

    public String getIdentificativo() {
        return identificativo;
    }

    private void setIdentificativo(String identificativo) {
        if (!isAvailable(identificativo))
            throw new IllegalArgumentException("Identificativo già presente nel sistema");
        this.identificativo = identificativo;
    }

    @Override
    public String toString() {
        return identificativo;
    }
}