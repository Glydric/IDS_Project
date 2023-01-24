package it.unicam.ids.studenti.ll.app.model;

import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.UpdatableProgrammaFedelta;

import java.util.List;
import java.util.Set;

//TODO separala da model e reimposta gradle cosi da essere un solo progetto
public class OfficeController {
    public UtenteIdentificabile utente;

    protected OfficeController(Identificatore identificatore, String password) {
        this(identificatore.toString(), password);
    }

    protected OfficeController(String identificatore, String password) throws IllegalArgumentException {
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
     * @param ragioneSociale l'identificatore
     * @return un'office controller generato grazie all'identificatore
     */
    public static OfficeController authenticatedByRegisterOf(String ragioneSociale) throws AuthorizationException {
        return authenticatedBy("register." + ragioneSociale, "");
    }

    public static OfficeController authenticatedBy(String identificatore, String password) {
        return new OfficeController(identificatore, password);
    }


    public void aggiungiDipendente(Persona persona, String password) throws AuthorizationException {
        aggiungiDipendente(persona);

        if (password != null)
            Identificatore
                    .getUtenteFrom(persona.nome + '.' + persona.cognome)
                    .setPassword(password);
    }

    public void aggiungiDipendente(Persona persona) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        utente.getAzienda().addDipendente(persona);
    }

    public void allowDipendente(String userName, String permesso) throws AuthorizationException {
        allowDipendente(
                (Dipendente) Identificatore.getUtenteFrom(userName),
                permesso
        );
    }
    public void allowDipendente(Dipendente dipendente, String permesso) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        utente.getAzienda().addPermessoDipendente(dipendente, permesso);
    }

    public void aggiungiCliente(Cliente cliente) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        ((Commerciante) utente.getAzienda()).addCliente(cliente);
    }

    public Set<ProgrammaFedelta> getProgrammiFrom(Cliente cliente) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        return ((Commerciante) utente.getAzienda()).getCoalizione().getProgrammi(cliente);
    }
    //TODO testare l'inserimento della vendita controllando se il cliente esiste nell'azienda

    public void inserimentoVendita(Cliente cliente, float prezzo) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");
        if (prezzo <= 0)
            throw new IllegalArgumentException("Il prezzo è inferiore a zero.");
        if (cliente == null)
            throw new IllegalArgumentException("Un cliente non può essere inesistente perl'azienda");
        if (!((Commerciante) utente.getAzienda()).getClienti().contains(cliente))
            throw new IllegalArgumentException("Cliente non esistente nell'azienda");
        ((Commerciante) utente.getAzienda()).getCoalizione().getProgrammi(cliente)
                .stream()
                .filter(
                        (programmaFedelta) -> (programmaFedelta instanceof UpdatableProgrammaFedelta)
                )
                .forEach(
                        (programmaFedelta) -> ((UpdatableProgrammaFedelta) programmaFedelta).aggiornaProgramma(prezzo)
                );
    }

    public List<Cliente> getListaClienti() throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        return ((Commerciante) utente.getAzienda())
                .getCoalizione()
                .getClienti()
                .stream()
                .toList();
    }
}
