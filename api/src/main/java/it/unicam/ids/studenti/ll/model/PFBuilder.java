package it.unicam.ids.studenti.ll.model;

public abstract class PFBuilder {

    private static Runnable function;

    /**
     * Si possono creare tutti i programmi a eccezione di programmaReferral che richiede un consumer per essere creato
     *
     * @param tipo il nome del programma da creare
     */
    public static ProgrammaFedelta buildProgramma(String tipo) throws IllegalArgumentException {
        return switch (tipo.toLowerCase()) {
            case "programmapunti", "punti" -> new ProgrammaPunti();
            case "programmalivelli", "livelli" -> new ProgrammaLivelli();
            case "programmacashback", "cashback" -> new ProgrammaCashback();
            case "programmavip", "vip" -> new ProgrammaVIP();
            case "programmareferral", "referral" -> new ProgrammaReferral(function);
            default -> throw new IllegalArgumentException("Programma sconosciuto");
        };
    }

    public static void setReferralFunction(Runnable function) throws IllegalArgumentException {
        if (function == null)
            throw new IllegalArgumentException("Function can't be null");
        PFBuilder.function = function;
    }

}