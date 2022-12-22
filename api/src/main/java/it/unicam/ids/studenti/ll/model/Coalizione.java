package it.unicam.ids.studenti.ll.model;

import java.util.*;
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

    /**
     *
     * @return i programmi in comune come classi
     */
    public Set<Class<? extends ProgrammaFedelta>> getCommonProgramsType() {
        return getCommonPrograms()
                .stream()
                .map(ProgrammaFedelta::getClass)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * @return tutti i programmi per come vengono visti nella `base dati`
     */
    public List<List<ProgrammaFedelta>> getListsPrograms() {
        return appartenenti
                .stream()
                .map(Commerciante::getListaProgrammi)
                .toList();
    }

    /**
     * @return i programmi in comune a tutti i commercianti.
     * È basato su equals per cui i programmi uguali ma con diversi attributi non sono considerati come uguali
     */
    public Set<ProgrammaFedelta> getCommonPrograms() {
        return getAllPrograms()
                .stream()
                .filter(programma ->
                        getListsPrograms()
                                .stream()
                                .allMatch(programmi ->
                                        programmi.contains(programma)
                                )
                )
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Concettualmente parliamo di un OR logico
     *
     * @return la lista di tutti i programmi disponibili escludendo quelli con stessi parametri (equals)
     */
    protected Set<ProgrammaFedelta> getAllPrograms() {
        return getListsPrograms()
                .stream()
                .flatMap(Collection::stream)
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

    private void mergeProgrammi(
            Set<ProgrammaFedelta> thisPrograms,
            Set<ProgrammaFedelta> otherPrograms,
            Map<Class<ProgrammaFedelta>, BiConsumer<ProgrammaFedelta, ProgrammaFedelta>> rules) {
        for (ProgrammaFedelta programmaFedelta : otherPrograms) {
            if (!thisPrograms.contains(programmaFedelta)) {
                thisPrograms.add(programmaFedelta);
            } else {
                // Supponiamo che ci sia solo un elemento
                List<ProgrammaFedelta> programmi = thisPrograms
                        .stream()
                        .filter(p1 -> p1.getClass() == programmaFedelta.getClass())
                        .toList();

                // Ci assicuriamo che sia cosi (anche se non ci sono motivi per cui non dovrebbe essere)
                assert (programmi.size() == 1);

                programmaFedelta.mergeProgrammi(
                        programmi.get(0),
                        rules.get(programmi.get(0).getClass())
                );
            }
        }
    }
}