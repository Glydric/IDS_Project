package it.unicam.ids.studenti.ll.model;

/**
 * Un'interfaccia funzionale
 */
@FunctionalInterface
public interface Permesso extends Runnable {
    @Override
    void run();
}