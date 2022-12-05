package it.unicam.ids.studenti.ll.model;

import java.util.*;

public class Commerciante extends Azienda {

    public List<Prodotto> listaProdotti = new ArrayList<>();
    public List<ProgrammaFedelta> listaProgrammi = new ArrayList<>();

    private final Map<Cliente, List<ProgrammaFedelta>> mapClienti = new HashMap<>();

    public Commerciante(String ragioneSociale) {
        super(ragioneSociale);
    }

    public Commerciante(String ragioneSociale, Date dataIscrizione) {
        super(ragioneSociale, dataIscrizione);
    }

    /**
     * @return la lista dei relativi clienti
     */
    public Set<Cliente> getClienti() {
        return mapClienti.keySet();
    }

    /**
     * @param programma
     */
    public void addProgramma(ProgrammaFedelta programma) {
        listaProgrammi.add(programma);

        getClienti().forEach(cliente -> mapClienti.get(cliente).add(programma));
        // TODO il programma aggiunto non deve avere lo stesso puntatore
        //  per ora i puntatori li ho fatti solo sul C.. dovremmo vederlo assieme magari.
    }

    public void addCliente(Cliente cliente){
        mapClienti.put(cliente, new ArrayList<>(listaProgrammi));
    }

    public List<ProgrammaFedelta> getProgrammi(Cliente cliente){
        return mapClienti.get(cliente);
    }
}