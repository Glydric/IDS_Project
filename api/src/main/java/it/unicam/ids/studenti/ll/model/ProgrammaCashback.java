package it.unicam.ids.studenti.ll.model;

import java.util.function.BiConsumer;

public class ProgrammaCashback implements ProgrammaFedelta {
    @Override
    public ProgrammaFedelta clone() {
        return new ProgrammaCashback();
    }

    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        return null;		//TODO

    }
    //TODO equals & hashcode
}