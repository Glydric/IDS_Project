package it.unicam.ids.studenti.ll.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Coalizione {
    private final Map<Cliente, Set<ProgrammaFedelta>> mapClienti = new HashMap<>();
    public Set<Commerciante> appartenenti = new HashSet<>();

    protected Coalizione() {
    }

    public Coalizione(Commerciante commerciante) {
        appartenenti.add(commerciante);
    }

    public Set<Cliente> getClienti() {
        return mapClienti.keySet();
    }

    protected void addProgrammaForEachCliente(ProgrammaFedelta programma) {
        //dato che il programma è stato precedentemente aggiunto nel commerciante, lui verrà considerato tra i programmi in comune
        // e se tutti i commercianti lo hanno definito come programma, questo sarà true e lo aggiungerà
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
        mapClienti.put(cliente, new HashSet<>(getCommonPrograms()));
    }

    public Set<Class<? extends ProgrammaFedelta>> getCommonProgramsType() {
        return getCommonPrograms()
                .stream()
                .map(ProgrammaFedelta::getClass)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * @return i programmi in comune a tutti i programmi fedelta basandosi su equals di ogni programma fedelta
     * i programmi uguali ma con diverse impostazioni non sono considerati come uguali
     */
    public Set<ProgrammaFedelta> getCommonPrograms() {
        Set<ProgrammaFedelta> commons = new HashSet<>(getAllPrograms());

        appartenenti
                .stream()
                .map(Commerciante::getListaProgrammi)
                .forEach(listaFedelta ->
                        commons.removeIf(p -> !listaFedelta.contains(p))
                );

        return commons;
    }

    /**
     * Concettualmente parliamo di un OR logico
     *
     * @return la lista di tutti i programmi disponibili escludendo quelli con stessi parametri (equals)
     */
    protected Set<ProgrammaFedelta> getAllPrograms() {
        return appartenenti
                .stream()
                .map(Commerciante::getProgrammi)
                .flatMap(Set::stream)
                .map(ProgrammaFedelta::clone)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<ProgrammaFedelta> getProgrammi(Cliente cliente) {
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
            // primo passo del merge è il merge dei clienti con i programmi
            Set<ProgrammaFedelta> otherPrograms = altraCoalizione.getProgrammi(c);

            if (!getClienti().contains(c)) {
                // se il cliente non esiste lo creiamo prendendo i programmi dall'altra coalizione
                mapClienti.put(c, otherPrograms);
            } else {
                mergeProgrammi(mapClienti.get(c), otherPrograms, mergeRules);
            }
        }
        return this;
    }

    protected void mergeProgrammi(
            Set<ProgrammaFedelta> thisPrograms,
            Set<ProgrammaFedelta> otherPrograms,
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