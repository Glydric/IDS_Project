package it.unicam.ids.studenti.ll.model;

import java.util.Objects;

public class Permesso{
    String permesso;

    private Permesso(String p) {
        if (p == null || Objects.equals(p, ""))
            throw new IllegalArgumentException("Il permesso non può essere nullo");
        this.permesso = p;
    }

    static Permesso from(String string) {
        return new Permesso(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permesso) && !(o instanceof String))
            return false;
        String actualString;
        if (o instanceof Permesso p)
            actualString = p.permesso;
        else actualString = (String) o;

        return Objects.equals(this.permesso, actualString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permesso);
    }
}