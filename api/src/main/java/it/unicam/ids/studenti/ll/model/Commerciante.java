package it.unicam.ids.studenti.ll.model;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;

public class Commerciante extends Azienda {
    private final Set<ProgrammaFedelta> listaProgrammi = new HashSet<>();
    private Coalizione gruppoAppartenza = new Coalizione(this);

    protected Commerciante(String ragioneSociale) {
        super(ragioneSociale);
    }

    public Commerciante(String ragioneSociale, LocalDate dataIscrizione, Proprietario proprietario) {
        super(ragioneSociale, dataIscrizione, proprietario);
    }

    protected List<ProgrammaFedelta> getListaProgrammi() {
        return getProgressi().stream().toList();
    }

    public Set<ProgrammaFedelta> getProgressi() {
        return Collections.unmodifiableSet(listaProgrammi);
    }

    /**
     * @param programma il nuovo programma
     */
    public void addNewProgramma(ProgrammaFedelta programma) {
        // Se la classe è già presente
        if (listaProgrammi.stream().map(Object::getClass).toList().contains(programma.getClass()))
            throw new IllegalArgumentException("Programma già presente");
        listaProgrammi.add(programma.clone());
        gruppoAppartenza.addProgrammaForEachCliente(programma);
    }

    /**
     * Esegue un merge dei gruppi di coalizione in questo e successivamente modifica il gruppo coalizione
     * dell'altro commerciante, le regole di merging sono tutte di default
     *
     * @param commerciante l'altro commerciante
     */
    public void mergeGroups(Commerciante commerciante) {
        mergeGroups(commerciante, null);
    }

    /**
     * Esegue un merge dei gruppi di coalizione in questo e successivamente modifica il gruppo coalizione
     * dell'altro commerciante
     *
     * @param commerciante l'altro commerciante
     * @param mergeRules   regole di default per ogni classe di tipo programma fedelta, ogni programma senza una
     *                     regola predefinita sfrutterà la propria regola di default
     */
    public void mergeGroups(Commerciante commerciante, Map<Class<ProgrammaFedelta>, BiConsumer<ProgrammaFedelta, ProgrammaFedelta>> mergeRules) {
        commerciante.setCoalizione(gruppoAppartenza.mergeCoalizioni(
                commerciante.gruppoAppartenza,
                mergeRules
        ));
    }

    /**
     * aggiunge il proprio tag alla coalizione e la si imposta come proprio gruppo appartenenza
     * ATTENZIONE LA COALIZIONE ATTUALE ANDRÀ PERSA
     *
     * @param coalizione la coalizione a cui ci si aggiunge
     * @return la coalizione precedente
     */
    protected Coalizione setCoalizione(Coalizione coalizione) {
        Coalizione oldGroup = gruppoAppartenza;
        gruppoAppartenza = coalizione;
        coalizione.appartenenti.add(this);
        return oldGroup;
    }

    public Coalizione getCoalizione() {
        return gruppoAppartenza;
    }

    /// metodi di comodo

    public List<Cliente> getClienti() {
        return gruppoAppartenza.getClienti().stream().toList();
    }

    public void addCliente(Cliente cliente) {
        gruppoAppartenza.addCliente(cliente);
    }

    protected List<ProgrammaFedelta> getProgressAsList(Cliente cliente) {
        return getProgress(cliente).stream().toList();
    }

    public Set<ProgrammaFedelta> getProgress(Cliente cliente) {
        return gruppoAppartenza.getProgrammi(cliente);
    }

    protected boolean clientHaveProgram(Cliente cliente, ProgrammaFedelta pf) {
        return getProgress(cliente)
                .stream()
                .map(Object::getClass)
                .toList()
                .contains(pf.getClass());
    }
}