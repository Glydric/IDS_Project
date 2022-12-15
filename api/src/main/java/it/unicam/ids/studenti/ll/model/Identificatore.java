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

    public void updateIdentificativo(String identificativo){
        setIdentificativo(identificativo);
        listaID.remove(identificativo);
    }
    private void setIdentificativo(String identificativo) {
        if (!isAvailable(identificativo))
            throw new IllegalArgumentException("Identificativo già presente nel sistema");
        if (identificativo.contains(" "))
            throw new IllegalArgumentException("Non è possibile inserire spazi nell'ID");
        this.identificativo = identificativo.toLowerCase();
    }

    @Override
    public String toString() {
        return identificativo;
    }
}