package it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta;

import java.util.Objects;
import java.util.function.BiConsumer;

public class ProgrammaVIP implements ProgrammaFedelta {
    public boolean isVip = false;

    protected ProgrammaVIP() {
    }

    protected ProgrammaVIP(boolean isVip) {
        this.isVip = isVip;
    }

    @Override
    public ProgrammaVIP clone() {
        try {
            return (ProgrammaVIP) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        System.out.println("utilizzo metodo merge di default, viene considerato vip se almeno uno dei due è vip");
        return (pr1, pr2) -> {
            ProgrammaVIP p1 = (ProgrammaVIP) pr1;
            ProgrammaVIP p2 = (ProgrammaVIP) pr2;
            p1.isVip = p1.isVip || p2.isVip;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammaVIP that)) return false;
        return isVip == that.isVip;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isVip);
    }
}