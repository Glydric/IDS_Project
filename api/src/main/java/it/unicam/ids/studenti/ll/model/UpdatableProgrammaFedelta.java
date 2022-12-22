package it.unicam.ids.studenti.ll.model;

public interface UpdatableProgrammaFedelta extends ProgrammaFedelta {
    //TODO aggiungere dei Runnable per definire le regole di aggiornamento

    /**
     * Prende in input il costo del prodotto e calcola
     * gli aggiornamenti da effettuare
     * @param value il costo del prodotto
     */
    void aggiornaProgramma(float value);

}