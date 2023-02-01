package it.unicam.ids.studenti.ll.app.model;

import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Coalizione {
    private UUID id;
    protected final Set<Cliente> clienti = new HashSet<>();
    protected final Set<Commerciante> appartenenti = new HashSet<>();

    protected Coalizione() {
    }

    public Coalizione(Commerciante commerciante) {
        appartenenti.add(commerciante);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<Cliente> getListaClienti() {
        return clienti.stream().toList();
    }

    protected Set<Cliente> getClienti() {
        return clienti;
    }

    protected Set<Commerciante> getAppartenenti() {
        return appartenenti;
    }

    protected void addProgrammaForEachCliente(ProgrammaFedelta programma) {
        //dato che il programma è stato precedentemente aggiunto nel commerciante, lui verrà considerato tra i programmi in comune
        // e se tutti i commercianti lo hanno definito come programma, questo sarà true e lo aggiungerà
        if (isProgramInCommons(programma))
            clienti.forEach(
                    cliente -> cliente
                            .mapCoalizione
                            .get(this)
                            .add(programma.clone())
            );
    }

    private boolean isProgramInCommons(ProgrammaFedelta programma) {
        return getCommonPrograms()
                .stream()
                .map(Object::getClass)
                .toList()
                .contains(programma.getClass());
    }

    protected void addCliente(Cliente cliente) {
        clienti.add(cliente);
        cliente
                .mapCoalizione
                .put(this, new HashSet<>(getCommonPrograms()));
    }

    /**
     * @return i programmi in comune come classi
     */
    protected Set<Class<? extends ProgrammaFedelta>> getCommonProgramsType() {
        return getCommonPrograms()
                .stream()
                .map(ProgrammaFedelta::getClass)
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * @return tutti i programmi per come vengono visti nella `base dati`
     */
    protected List<List<ProgrammaFedelta>> getListsPrograms() {
        return appartenenti
                .stream()
                .map(Commerciante::getListaProgrammi)
                .toList();
    }

    /**
     * @return i programmi in comune a tutti i commercianti.
     * È basato su equals per cui i programmi uguali ma con diversi attributi non sono considerati come uguali
     */
    protected Set<ProgrammaFedelta> getCommonPrograms() {
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

    private Set<ProgrammaFedelta> getProgrammiFilteredBy(Predicate<Cliente> filteringRule) throws NullPointerException {
        List<Cliente> cliente = clienti
                .stream()
                .filter(
                        filteringRule
                ).toList();

        assert cliente.size() <= 1;

        if (cliente.size() == 0)
            throw new NullPointerException("Nome utente o password errata");

        return cliente.get(0).getProgressIn(this);
    }

    protected Set<ProgrammaFedelta> getProgrammiOf(String tessera) throws NullPointerException {
        return getProgrammiFilteredBy(c -> c.identificativoTessera.toString().equals(tessera));
    }

    protected Set<ProgrammaFedelta> getProgrammiOf(String tessera, String password) throws NullPointerException {
        return getProgrammiFilteredBy(c -> c.isValid(tessera, password));
    }


    /**
     * Ottiene i clienti dalla propria coalizione
     *
     * @param message il messaggio
     */
    protected void sendMessageToAll(String message) {
        SingletonSMS.getEntity().inviaMessaggio(
                clienti,
                message
        );
    }

    /**
     * @param cliente il cliente della coalizione a cui inviare il messaggio
     * @param message il messaggio
     */
    protected void sendMessage(Cliente cliente, String message) {
        if (clienti.contains(cliente))
            SingletonSMS.getEntity().inviaMessaggio(cliente, message);
    }

    /**
     * Esegue il merge delle informazioni per ogni utente,
     * aggiungendo le informazioni contenute nella coalizione fornita in questa
     *
     * @param altraCoalizione l'altra coalizione con cui unire questa
     */
    protected Coalizione mergeCoalizioni(
            Coalizione altraCoalizione,
            Map<Class<ProgrammaFedelta>, BiConsumer<ProgrammaFedelta, ProgrammaFedelta>> mergeRules) {
        for (Cliente c : altraCoalizione.getClienti()) {
            // primo passo del merge è il merge dei clienti con i programmi
            Set<ProgrammaFedelta> otherPrograms =
                    c.getProgressIn(altraCoalizione);

            if (!getClienti().contains(c)) {
                // se il cliente non esiste lo creiamo prendendo i programmi dall'altra coalizione
                clienti.add(c);
                c.mapCoalizione.put(this, otherPrograms);
            } else {
                mergeProgrammi(
                        c.mapCoalizione.get(this),
                        otherPrograms,
                        mergeRules
                );
            }
        }
        return this;
    }

    protected void mergeProgrammi(
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coalizione that)) return false;

        return getId() != null
                ? getId().equals(that.getId())
                : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}