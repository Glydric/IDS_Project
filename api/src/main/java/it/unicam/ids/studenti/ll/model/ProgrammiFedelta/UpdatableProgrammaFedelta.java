package it.unicam.ids.studenti.ll.model.ProgrammiFedelta;

import java.util.function.BiConsumer;

/**
 * Un programma fedelta aggiornabile in base al prezzo
 */
public interface UpdatableProgrammaFedelta extends ProgrammaFedelta {
    /**
     * Prende in input il costo del prodotto e calcola
     * gli aggiornamenti da effettuare
     *
     * @param value il costo del prodotto
     */
    void aggiornaProgramma(float value);

    interface DefaultRule {
        BiConsumer<ProgrammaCashback, Float> programmaCashback = ProgrammaCashback::addCashback;
        BiConsumer<ProgrammaLivelli, Float> programmaLivelli = (programma, value) -> programma.increaseLivello((short) 1);
        BiConsumer<ProgrammaPunti, Float> programmaPunti = (programma, value) -> programma.setPunti(value.intValue());
    }
}