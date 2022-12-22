package it.unicam.ids.studenti.ll.model;

public abstract class PFBuilder {
    /**
     * Si possono creare tutti i programmi ad eccezione di programmaReferral che richiede un consumer per essere creato
     *
     * @param tipo il nome del programma da creare
     */
    public static ProgrammaFedelta buildProgramma(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "programmapunti", "punti" -> new ProgrammaPunti();
            case "programmalivelli", "livelli" -> new ProgrammaLivelli();
            case "programmacashback", "cashback" -> new ProgrammaCashback();
            case "programmavip", "vip" -> new ProgrammaVIP();
            default -> throw new IllegalArgumentException("Programma non esistente");
        };
    }

}