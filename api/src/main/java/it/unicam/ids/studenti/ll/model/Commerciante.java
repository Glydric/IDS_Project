package it.unicam.ids.studenti.ll.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commerciante extends Azienda {
    public List<ProgrammaFedelta> listaProgrammi = new ArrayList<>();
    public Coalizione gruppoAppartenza = new Coalizione(this);

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
        listaProgrammi.add(programma);
        gruppoAppartenza.addProgrammaForEachCliente(programma);
        // TODO il programma aggiunto non deve avere lo stesso puntatore
    }


    /// metodi di comodo

    public List<Cliente> getClienti() {
        return gruppoAppartenza.getClienti().stream().toList();
    }

    public void addCliente(Cliente c2) {
        gruppoAppartenza.addCliente(c2);
    }

    public List<ProgrammaFedelta> getProgress(Cliente c1) {
        return gruppoAppartenza.getProgrammi(c1);
    }
}