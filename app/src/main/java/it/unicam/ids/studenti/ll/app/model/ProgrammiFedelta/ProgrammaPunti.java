package it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta;

import java.util.Objects;
import java.util.function.BiConsumer;

public class ProgrammaPunti implements UpdatableProgrammaFedelta {
    private int punti = 0;
    public BiConsumer<ProgrammaPunti, Float> rule = DefaultRule.programmaPunti;

    protected ProgrammaPunti() {
    }

    protected ProgrammaPunti(int punti) {
        setPunti(punti);
    }

    public int getPunti() {
        return this.punti;
    }

    public void setPunti(int punti) {
        if (punti < 0)
            throw new IllegalArgumentException("Numero punti inseriti è inferiore a 0");
        this.punti = punti;
    }

    public void addPunti(int punti) {
        setPunti(punti + this.punti);
    }

    @Override
    public void aggiornaProgramma(float value) {
        rule.accept(this, value);
    }

    @Override
    public ProgrammaPunti clone() {
        try {
            return (ProgrammaPunti) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        System.out.println("utilizzo metodo merge di default, viene eseguita la somma");
        return (pr1, pr2) -> {
            ProgrammaPunti p1 = (ProgrammaPunti) pr1;
            ProgrammaPunti p2 = (ProgrammaPunti) pr2;
            p1.setPunti(p2.getPunti() + p1.getPunti());
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammaPunti that)) return false;
        return getPunti() == that.getPunti();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPunti());
    }

}