package it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta;

import java.util.UUID;
import java.util.function.BiConsumer;

public class ProgrammaReferral implements ProgrammaFedelta {
    public final String codiceInvito = UUID.randomUUID().toString();
    private final Runnable function;

    /**
     * Nel costruttore di un programma referral possiamo inserire come parametro
     * una funzione con un altro parametro da incrementare una volta che il referral è stato usato da un utente
     *
     * @param function la 'funzione'
     */
    protected ProgrammaReferral(Runnable function) throws IllegalArgumentException{
        if(function==null)
            throw new IllegalArgumentException("Function can't be null");
        this.function = function;
    }

    public void useCode() {
        function.run();
    }

    @Override
    public ProgrammaReferral clone() {
        try {
            return (ProgrammaReferral) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        return (pr1, pr2) -> {
        };
    }
}