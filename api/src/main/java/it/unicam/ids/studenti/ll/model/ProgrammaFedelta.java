package it.unicam.ids.studenti.ll.model;

import java.util.function.BiConsumer;

public interface ProgrammaFedelta extends Cloneable {
    //TODO aggiungere dei Runnable per definire le regole di aggiornamento

    void aggiornaProgramma(float value);

    /**
     * @param otherProgramma l'altro programma fedelta
     * @param rule           come unire i programmi
     */
    default void mergeProgrammi(ProgrammaFedelta otherProgramma, BiConsumer<ProgrammaFedelta, ProgrammaFedelta> rule) {
        if (otherProgramma.getClass() != this.getClass())
            throw new IllegalArgumentException("La classe deve essere la stessa");

        if (rule == null) {
            System.out.print("Regola fornita nulla, utilizzo");
            rule = getDefaultConsumer();
        }

        rule.accept(otherProgramma, this);
    }

    ProgrammaFedelta clone();

    BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer();
}