package it.unicam.ids.studenti.ll.model;

import java.util.function.BiConsumer;

public class ProgrammaPunti implements ProgrammaFedelta {

    private int punti = 0;

    public int getPunti() {
        return this.punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
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
}