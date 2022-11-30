package it.unicam.ids.studenti.ll.model;

import java.util.*;

public class Commerciante extends Azienda {

    public Magazzino magazzino = new Magazzino();
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
     * @param programma il nuovo programma
     */
    public void addNewProgramma(ProgrammaFedelta programma) {
        listaProgrammi.add(programma);

        getClienti().forEach(cliente -> mapClienti.get(cliente).add(programma));
        // TODO il programma aggiunto non deve avere lo stesso puntatore
    }

    public void addCliente(Cliente cliente){
        mapClienti.put(cliente, new ArrayList<>(listaProgrammi));
    }

    public List<ProgrammaFedelta> getProgrammi(Cliente cliente){
        return mapClienti.get(cliente);
    }
}