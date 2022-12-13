package it.unicam.ids.studenti.ll.model;

public interface UpdatableProgrammaFedelta extends ProgrammaFedelta {
	//TODO aggiungere dei Runnable per definire le regole di aggiornamento

	/**
	 * Dovrebbe usare un BiConsumer per calcolare la regola
	 * @param value il costo del prodotto
	 */
	void aggiornaProgramma(float value);

}