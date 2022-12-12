package it.unicam.ids.studenti.ll.model;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ProgrammaReferral implements ProgrammaFedelta {

    private final String codiceInvito;

    /**
     * Nel costruttore di un programma referral possiamo inserire come paramentro un'altro programma per definire il vantaggio
     *
     * @param consumer
     * @param codiceInvito
     */
    public ProgrammaReferral(Consumer<ProgrammaFedelta> consumer, String codiceInvito) {
        this.codiceInvito = codiceInvito;
        // TODO - implement ProgrammaReferral.ProgrammaReferral
        throw new UnsupportedOperationException();
    }

    public String getCodiceInvito() {
        return this.codiceInvito;
    }

    @Override
    public ProgrammaFedelta clone() {
        try {
            return (ProgrammaPunti) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BiConsumer<ProgrammaFedelta, ProgrammaFedelta> getDefaultConsumer() {
        return (pr1, pr2) -> {
            ProgrammaReferral p1 = (ProgrammaReferral) pr1;
            ProgrammaReferral p2 = (ProgrammaReferral) pr2;
        };
    }
}