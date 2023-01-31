package it.unicam.ids.studenti.ll.app.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;

public class Commerciante extends Azienda {
    private final Set<ProgrammaFedelta> listaProgrammi = new HashSet<>();
    private String linkEsterno;
    private Coalizione gruppoAppartenza = new Coalizione(this);
    private Commerciante wantCoalize;

    /**
     * Used by spring
     */
    protected Commerciante() {
        super("");
    }

    @JsonCreator
    protected Commerciante(
            String ragioneSociale,
            int anno,
            int mese,
            int giorno
    ) {
        super(
                ragioneSociale,
                LocalDate.of(anno, mese, giorno)
        );
    }

    protected Commerciante(String ragioneSociale, LocalDate dataIscrizione) {
        super(ragioneSociale, dataIscrizione);
    }

    protected Commerciante(String ragioneSociale) {
        super(ragioneSociale);
    }


    protected Commerciante(String ragioneSociale, LocalDate dataIscrizione, Proprietario proprietario) {
        super(ragioneSociale, dataIscrizione, proprietario);
    }

    protected List<ProgrammaFedelta> getListaProgrammi() {
        return getProgrammi().stream().toList();
    }

    protected Set<ProgrammaFedelta> getProgrammi() {
        return Collections.unmodifiableSet(listaProgrammi);
    }

    /**
     * @param programma il nuovo programma
     */
    protected void addNewProgramma(ProgrammaFedelta programma) throws IllegalArgumentException {
        // Se la classe è già presente
        if (listaProgrammi
                .stream()
                .map(Object::getClass)
                .toList()
                .contains(programma.getClass())
        )
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
    protected void mergeGroups(Commerciante commerciante) throws IllegalStateException {
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
    protected void mergeGroups(
            Commerciante commerciante,
            Map<
                    Class<ProgrammaFedelta>,
                    BiConsumer<ProgrammaFedelta, ProgrammaFedelta>
                    > mergeRules) throws IllegalStateException {

        setWantCoalize(commerciante);
        if (!commerciante.wantCoalizeWith(this)) {
            throw new IllegalStateException("Attendere che l'altro commerciante accetti la richiesta");
        }
        commerciante.setCoalizioneGettingOld(gruppoAppartenza.mergeCoalizioni(
                commerciante.gruppoAppartenza,
                mergeRules
        ));
    }

    protected boolean wantCoalizeWith(Commerciante commerciante) {
        return wantCoalize != null && wantCoalize.equals(commerciante);
    }

    public Commerciante getWantCoalize() {
        return wantCoalize;
    }

    public void setWantCoalize(Commerciante commerciante) {
        wantCoalize = commerciante;
    }

    /**
     * aggiunge il proprio tag alla coalizione e la si imposta come proprio gruppo appartenenza
     * ATTENZIONE LA COALIZIONE ATTUALE ANDRÀ PERSA
     *
     * @param coalizione la coalizione a cui ci si aggiunge
     * @return la coalizione precedente
     */
    protected Coalizione setCoalizioneGettingOld(Coalizione coalizione) {
        Coalizione oldGroup = gruppoAppartenza;
        setCoalizione(coalizione);
        return oldGroup;
    }

    public Coalizione getCoalizione() {
        return gruppoAppartenza;
    }

    public void setCoalizione(Coalizione coalizione) {
        gruppoAppartenza = coalizione;
        coalizione.appartenenti.add(this);
    }

    /// metodi di comodo

    protected List<Cliente> getClienti() {
        return gruppoAppartenza.getClienti().stream().toList();
    }

    protected void addCliente(Cliente cliente) {
        gruppoAppartenza.addCliente(cliente);
    }

    protected List<ProgrammaFedelta> getProgressAsList(Cliente cliente) {
        return getProgress(cliente).stream().toList();
    }

    protected Set<ProgrammaFedelta> getProgress(Cliente cliente) {
        return cliente
                .mapCoalizione
                .get(gruppoAppartenza);
    }

    protected boolean clientHaveProgram(Cliente cliente, ProgrammaFedelta pf) {
        return getProgress(cliente)
                .stream()
                .map(Object::getClass)
                .toList()
                .contains(pf.getClass());
    }

    protected String getLinkEsterno() {
        return linkEsterno;
    }

    /**
     * La possibilità di aggiungere un link esterno per il negozio (ex. Google my business)
     *
     * @param link da inserire
     */
    protected void setLinkEsterno(String link) {
        if (link == null)
            return;
        if (!Pattern
                .compile("(https?://)(w{3}\\.)?([^(w{3})]\\w*\\.\\w*[^.\\s])")
                .matcher(link)
                .find())
            throw new IllegalArgumentException("Link " + link + "non valido");

        this.linkEsterno = link;
    }

}