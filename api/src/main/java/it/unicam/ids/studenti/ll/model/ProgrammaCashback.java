package it.unicam.ids.studenti.ll.model;

import java.util.Objects;
import java.util.function.BiConsumer;

public class ProgrammaCashback implements ProgrammaFedelta {
    private float cashback = 0;

    public ProgrammaCashback() {
    }

    public ProgrammaCashback(float initCashback) {
        cashback = initCashback;
    }

    public void addCashback(float add) {
        if (add < 0) throw new IllegalArgumentException("Valore add negativo!!");
        cashback += add;
    }

    public float getCashback() {
        return cashback;
    }

    public float resetCashback() {
        float oldCashback = cashback;
        cashback = 0;
        return oldCashback;
    }

    @Override
    public ProgrammaCashback clone() {
        try {
            return (ProgrammaCashback) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        return (pr1, pr2) -> {
            ProgrammaCashback p1 = (ProgrammaCashback) pr1;
            ProgrammaCashback p2 = (ProgrammaCashback) pr2;
            p1.addCashback(p2.getCashback());
        };

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammaCashback that)) return false;
        return Float.compare(that.getCashback(), getCashback()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCashback());
    }
}