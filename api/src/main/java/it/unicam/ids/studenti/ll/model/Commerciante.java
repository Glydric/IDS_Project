package it.unicam.ids.studenti.ll.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class Commerciante extends Azienda {
    public List<ProgrammaFedelta> listaProgrammi = new ArrayList<>();
    private Coalizione gruppoAppartenza = new Coalizione(this);

    public Commerciante(String ragioneSociale) {
        super(ragioneSociale);
    }

    public Commerciante(String ragioneSociale, Date dataIscrizione) {
        super(ragioneSociale, dataIscrizione);
    }

    public List<ProgrammaFedelta> getListaProgrammi() {
        return listaProgrammi;
    }

    /**
     * @param programma il nuovo programma
     */
    public void addNewProgramma(ProgrammaFedelta programma) {
        listaProgrammi.add(programma.clone());
        gruppoAppartenza.addProgrammaForEachCliente(programma);
        // TODO il programma aggiunto non deve avere lo stesso puntatore
    }

    /**
     * Esegue un merge dei gruppi di coalizione in questo e successivamente modifica il gruppo coalizione
     * dell'altro commerciante, le regole di merging sono tutte di default
     *
     * @param commerciante l'altro commerciante
     */
    public void merge(Commerciante commerciante) {
        merge(commerciante, null);
    }

    /**
     * Esegue un merge dei gruppi di coalizione in questo e successivamente modifica il gruppo coalizione
     * dell'altro commerciante
     *
     * @param commerciante l'altro commerciante
     * @param mergeRules regole di default per ogni classe di tipo programma fedelta, ogni programma senza una
     *                   regola predefinita sfrutterà la propria regola di default
     */
    public void merge(Commerciante commerciante, Map<Class<ProgrammaFedelta>, BiConsumer<ProgrammaFedelta, ProgrammaFedelta>> mergeRules) {
        gruppoAppartenza.mergeCoalizioni(commerciante.gruppoAppartenza, mergeRules);
        commerciante.setCoalizione(gruppoAppartenza);
    }

    /**
     * aggiunge il proprio tag alla coalizione e la si imposta come proprio gruppo appartenenza
     * ATTENZIONE LA COALIZIONE ATTUALE ANDRÀ PERSA
     * @param coalizione la coalizione a cui ci si aggiunge
     * @return la coalizione precedente
     */
    protected Coalizione setCoalizione(Coalizione coalizione){
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

    public List<ProgrammaFedelta> getProgress(Cliente cliente) {
        return gruppoAppartenza.getProgrammi(cliente);
    }
}