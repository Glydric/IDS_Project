package it.unicam.ids.studenti.ll.model;

import java.util.*;
import java.util.function.BiConsumer;

public class Coalizione {
    private final Map<Cliente, List<ProgrammaFedelta>> mapClienti = new HashMap<>();
    public List<Commerciante> appartenenti = new ArrayList<>();

    protected Coalizione() {
    }

    public Coalizione(Commerciante commerciante) {
        appartenenti.add(commerciante);
    }

    public Set<Cliente> getClienti() {
        return mapClienti.keySet();
    }

    public void addProgrammaForEachCliente(ProgrammaFedelta programma) {
        if (isProgramInCommons(programma))
            getClienti().forEach(cliente -> mapClienti.get(cliente).add(programma.clone()));
    }

    private boolean isProgramInCommons(ProgrammaFedelta programma) {
        return getCommonPrograms()
                .stream()
                .map(Object::getClass)
                .toList()
                .contains(programma.getClass());
    }

    public void addCliente(Cliente cliente) {
        mapClienti.put(cliente, new ArrayList<>(getCommonPrograms()));
    }

    /**
     * @return i programmi in comune a tutti i programmi fedelta
     */
    public List<ProgrammaFedelta> getCommonPrograms() {
        List<ProgrammaFedelta> commons = new ArrayList<>(getAllPrograms());

        System.out.println("new");
        List<List<ProgrammaFedelta>> l = appartenenti
                .stream()
                .map(Commerciante::getListaProgrammi)
                .toList();

        for (List<ProgrammaFedelta> listaFedelta : l) {
            List<? extends Class<?>> list = listaFedelta
                    .stream()
                    .map(Object::getClass)
                    .toList();

            commons.removeIf(p -> !list.contains(p.getClass()));
        }
        return commons
                .stream()
                .distinct()
//                .map(ProgrammaFedelta::clone)
                .toList();
    }

    /**
     * Concettualmente parliamo di un OR logico
     *
     * @return la lista di tutti i programmi
     */
    protected List<ProgrammaFedelta> getAllPrograms() {
        return appartenenti
                .stream()
                .map(Commerciante::getListaProgrammi)
                .flatMap(List::stream)
                .map(ProgrammaFedelta::clone)
                .toList();
    }

    public List<ProgrammaFedelta> getProgrammi(Cliente cliente) {
        return mapClienti.get(cliente);
    }

    /**
     * Esegue il merge delle informazioni per ogni utente,
     * aggiungendo le informazioni contenute nella coalizione fornita in questa
     *
     * @param altraCoalizione l'altra coalizione con cui unire questa
     */
    public Coalizione mergeCoalizioni(
            Coalizione altraCoalizione,
            Map<Class<ProgrammaFedelta>, BiConsumer<ProgrammaFedelta, ProgrammaFedelta>> mergeRules) {
        for (Cliente c : altraCoalizione.getClienti()) {
            List<ProgrammaFedelta> otherPrograms = altraCoalizione.getProgrammi(c);

            if (!getClienti().contains(c)) {
                mapClienti.put(c, new ArrayList<>(otherPrograms));
            } else {
                mergeProgrammi(mapClienti.get(c), otherPrograms, mergeRules);
            }
        }
        return this;
        // TODO - test Coalizione.mergeCoalizioni
    }

    protected void mergeProgrammi(
            List<ProgrammaFedelta> thisPrograms,
            List<ProgrammaFedelta> otherPrograms,
            Map<Class<ProgrammaFedelta>, BiConsumer<ProgrammaFedelta, ProgrammaFedelta>> rules) {
        for (ProgrammaFedelta p1 : otherPrograms) {
            if (!thisPrograms.contains(p1)) {
                thisPrograms.add(p1);
            } else {
                ProgrammaFedelta p2 = thisPrograms
                        .stream()
                        .filter((p) -> p.getClass() == p1.getClass())
                        .toList()
                        .get(0);

                BiConsumer<ProgrammaFedelta, ProgrammaFedelta> c = rules.get(p2.getClass());

                p1.mergeProgrammi(p2, c);
            }
        }
    }
}