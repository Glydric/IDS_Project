package it.unicam.ids.studenti.ll.model;

import java.util.function.BiConsumer;

public class ProgrammaCashback implements ProgrammaFedelta {
    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        return null;		//TODO

    }
}