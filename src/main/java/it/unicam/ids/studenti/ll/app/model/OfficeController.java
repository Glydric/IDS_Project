package it.unicam.ids.studenti.ll.app.model;

import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.UpdatableProgrammaFedelta;

import java.util.List;
import java.util.Set;

public class OfficeController {
    public UtenteIdentificabile utente;

    protected OfficeController(Identificatore identificatore, String password) {
        this(identificatore.toString(), password);
    }

    protected OfficeController(
            String identificatore,
            String password
    ) throws IllegalArgumentException {
        utente = Identificatore.getUtenteFrom(identificatore);

        if (utente == null)
            throw new IllegalArgumentException("Autorizzatore non esistente");

        if (!utente.isPasswordValid(password))
            throw new IllegalArgumentException("Login errato");

        if (utente.getAzienda() == null)
            throw new IllegalArgumentException("Non possedete alcuna azienda");
    }


    /**
     * la password viene considerata vuota
     *
     * @param commerciante l'identificatore
     * @return un'office controller generato grazie all'identificatore
     */
    public static OfficeController authenticatedByRegisterOf(Commerciante commerciante) throws AuthorizationException {
        Register.initFrom(commerciante);
        return authenticatedBy(Identificatore.getFormat("register", commerciante.getRagioneSociale()), "");

    }

    public static OfficeController authenticatedBy(String identificatore, String password) {
        return new OfficeController(identificatore, password);
    }

    public Commerciante getCommerciante() {
        return (Commerciante) utente.getAzienda();
    }

    public void aggiungiDipendente(Persona persona, String password) throws AuthorizationException {
        aggiungiDipendente(persona);

        if (password != null)
            Identificatore
                    .getUtenteFrom(persona.nome + '.' + persona.cognome)
                    .setPassword(password);
    }

    public void aggiungiDipendente(Persona persona) throws AuthorizationException {
        utente.authorize();

        utente.getAzienda().addDipendente(persona);
    }

    public void allowDipendente(String userName, String permesso) throws AuthorizationException {
        allowDipendente(
                (Dipendente) Identificatore.getUtenteFrom(userName),
                permesso
        );
    }

    public void allowDipendente(Dipendente dipendente, String permesso) throws AuthorizationException {
        utente.authorize();

        utente.getAzienda().addPermessoDipendente(dipendente, permesso);
    }

    public void aggiungiCliente(Cliente cliente) throws AuthorizationException {
        utente.authorize();

        getCommerciante().getCoalizione().addCliente(cliente);
    }

    public void aggiungiProgramma(ProgrammaFedelta pf) throws AuthorizationException, IllegalArgumentException {
        utente.authorize();

        getCommerciante().addNewProgramma(pf);
    }

    public Set<ProgrammaFedelta> getProgrammi(String tessera) throws AuthorizationException {
        utente.authorize();

        return getCommerciante()
                .getCoalizione()
                .getProgrammiOf(tessera);
    }

    public Set<ProgrammaFedelta> getProgrammiOf(String tessera, String password) throws AuthorizationException {
        utente.authorize();

        return getCommerciante()
                .getCoalizione()
                .getProgrammiOf(
                        tessera,
                        password
                );
    }

    public void inserimentoVendita(Cliente cliente, float prezzo) throws AuthorizationException {
        utente.authorize();

        if (prezzo <= 0)
            throw new IllegalArgumentException("Il prezzo è inferiore a zero.");
        if (cliente == null)
            throw new IllegalArgumentException("Un cliente non può essere inesistente perl'azienda");
        if (!getCommerciante().getCoalizione().getListaClienti().contains(cliente))
            throw new IllegalArgumentException("Cliente non esistente nell'azienda");

        cliente.getProgressIn(getCommerciante().getCoalizione())
                .stream()
                .filter(
                        (programmaFedelta) -> (programmaFedelta instanceof UpdatableProgrammaFedelta)
                )
                .forEach(
                        (programmaFedelta) -> ((UpdatableProgrammaFedelta) programmaFedelta).aggiornaProgramma(prezzo)
                );
    }

    public List<Cliente> getListaClienti() throws AuthorizationException {
        utente.authorize();

        return getCommerciante()
                .getCoalizione()
                .getListaClienti();
    }

    public void coalizzaCon(Commerciante commerciante) throws AuthorizationException, IllegalStateException {
        utente.authorize();

        getCommerciante().mergeGroups(commerciante);
    }
}
