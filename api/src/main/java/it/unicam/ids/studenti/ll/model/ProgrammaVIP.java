package it.unicam.ids.studenti.ll.model;

import java.util.function.BiConsumer;

public class ProgrammaVIP implements ProgrammaFedelta {

    public boolean isVip = false;

    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        System.out.println("utilizzo metodo merge di default, viene considerato vip se almeno uno dei due è vip");
        return (pr1, pr2) -> {
            ProgrammaVIP p1 = (ProgrammaVIP) pr1;
            ProgrammaVIP p2 = (ProgrammaVIP) pr2;
            p1.isVip = p1.isVip || p2.isVip;
        };
    }
}