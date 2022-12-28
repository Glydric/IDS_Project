package it.unicam.ids.studenti.ll.model;

import java.util.function.BiConsumer;

public interface ProgrammaFedelta extends Cloneable {

    static ProgrammaFedelta create(String type) throws IllegalArgumentException {
        return create().setType(type).build();
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
        private static Runnable function;
        private static String type;

        private Builder() {
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

        public Builder setType(String type) {
            Builder.type = type;
            return this;
        }

        /**
         * This method is used only with construction of ProgrammaReferral
         * @param function, the function to pass to ProgrammaReferral on Build
         * @return this object
         */
        public Builder setReferralFunction(Runnable function) {
            Builder.function = function;
            return this;
        }
    }
}