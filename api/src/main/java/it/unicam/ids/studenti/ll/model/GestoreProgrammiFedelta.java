package it.unicam.ids.studenti.ll.model;

import java.util.List;
import java.util.Map;

public class GestoreProgrammiFedelta {

    public Map<Cliente, List<ProgrammaFedelta>> mapProgrammiCliente;
    BidirectionalArrayMap<Commerciante, Cliente> mapCommerciante;

    /**
     * @param commerciante
     * @param programma
     */
    public void addProgramma(Commerciante commerciante, Class<ProgrammaFedelta> programma) {
        // TODO - implement GestoreProgrammiFedelta.addProgramma
        throw new UnsupportedOperationException();
    }

    /**
     * @param commerciante
     */
    public List<Class<ProgrammaFedelta>> getProgrammi(Commerciante commerciante) {
        // TODO - implement GestoreProgrammiFedelta.getProgrammi
        throw new UnsupportedOperationException();
    }

}