package it.unicam.ids.studenti.ll.model.ProgrammiFedelta;

import java.util.Objects;
import java.util.function.BiConsumer;

public class ProgrammaLivelli implements UpdatableProgrammaFedelta {
    private short livello = 0;
    public BiConsumer<ProgrammaLivelli, Float> rule = DefaultRule.programmaLivelli;

    protected ProgrammaLivelli() {
    }

    protected ProgrammaLivelli(short livello) {
        setLivello(livello);
    }

    public short getLivello() {
        return this.livello;
    }

    public void setLivello(short livello) {
        this.livello = livello;
    }

    public void increaseLivello(short livello) {
        setLivello((short) Math.min(Short.MAX_VALUE, this.livello + livello));
    }

    @Override
    public void aggiornaProgramma(float value) {
        rule.accept(this, value);
    }

    @Override
    public ProgrammaLivelli clone() {
        try {
            return (ProgrammaLivelli) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        System.out.println("utilizzo metodo merge di default, viene preso il livello più alto");
        return (pr1, pr2) -> {
            ProgrammaLivelli p2 = ((ProgrammaLivelli) pr2);
            ProgrammaLivelli p1 = ((ProgrammaLivelli) pr2);
            short max = (short) Math.max(p1.getLivello(), p2.getLivello());
            p1.setLivello(max);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammaLivelli that)) return false;
        return getLivello() == that.getLivello();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLivello());
    }
}