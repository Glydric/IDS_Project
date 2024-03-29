package it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.function.BiConsumer;

public interface ProgrammaFedelta extends Cloneable {


    static ProgrammaFedelta create(String type) throws IllegalArgumentException {
        return create().setType(type).build();
    }

    @JsonCreator
    static <T extends Serializable> ProgrammaFedelta jsonBuilder(
            String tipo,
            Runnable referralFunction,
            T value
    ) {
        return create()
                .setType(tipo)
                .setReferralFunction(referralFunction)
                .buildWith(value);
    }

    static Builder create() {
        return new Builder();
    }

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

    class Builder {
        private Runnable function;
        private String type;

        private Builder() {
        }


        /**
         * Si possono creare tutti i programmi, in programmaReferral si solleva un'eccezione se non inseriamo la function
         */
        public <T extends Serializable> ProgrammaFedelta buildWith(T value) throws IllegalArgumentException {
            return switch (type.toLowerCase()) {
                case "programmapunti", "punti", "punto" -> new ProgrammaPunti((int) value);
                case "programmalivelli", "livelli", "livello" -> new ProgrammaLivelli((short) value);
                case "programmacashback", "cashback" -> new ProgrammaCashback((float) value);
                case "programmavip", "vip" -> new ProgrammaVIP((boolean) value);
                case "programmareferral", "referral" -> new ProgrammaReferral(function);
                default -> throw new IllegalArgumentException("Programma sconosciuto o non impostato");
            };
        }

        /**
         * Si possono creare tutti i programmi, in programmaReferral si solleva un'eccezione se non inseriamo la function
         */
        public ProgrammaFedelta build() throws IllegalArgumentException {
            return switch (type.toLowerCase()) {
                case "programmapunti", "punti", "punto" -> new ProgrammaPunti();
                case "programmalivelli", "livelli", "livello" -> new ProgrammaLivelli();
                case "programmacashback", "cashback" -> new ProgrammaCashback();
                case "programmavip", "vip" -> new ProgrammaVIP();
                case "programmareferral", "referral" -> new ProgrammaReferral(function);
                default -> throw new IllegalArgumentException("Programma sconosciuto o non impostato");
            };
        }

        /**
         * Consente di impostare il tipo degli elementi da creare
         *
         * @param type, il tipo come stringa
         * @return questo builder
         */
        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        /**
         * This method is used only with construction of ProgrammaReferral
         *
         * @param function, the function to pass to ProgrammaReferral on Build
         * @return this object
         */
        public Builder setReferralFunction(Runnable function) {
            this.function = function;
            return this;
        }

    }
}