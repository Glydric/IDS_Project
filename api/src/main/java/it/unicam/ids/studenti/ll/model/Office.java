package it.unicam.ids.studenti.ll.model;

import it.unicam.ids.studenti.ll.model.ProgrammiFedelta.ProgrammaFedelta;
import it.unicam.ids.studenti.ll.model.ProgrammiFedelta.UpdatableProgrammaFedelta;

import java.util.Set;

public class Office {
    public UtenteIdentificabile utente;

    public Office(Identificatore identificatore, String password) {
        this(identificatore.toString(), password);
    }

    public Office(String identificatore, String password) throws IllegalArgumentException {
        utente = Identificatore.getUtenteFrom(identificatore);

        if (utente == null || !utente.isPasswordValid(password))
            throw new IllegalArgumentException("Login errato");

        if (utente.getAzienda() == null)
            throw new IllegalArgumentException("Non possedete alcuna azienda");
    }


    public void aggiungiDipendente(Persona persona) throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        utente.getAzienda().addDipendente(persona);
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

    public Set<Cliente> getListaClienti() throws AuthorizationException {
        if (!utente.haveAuthorization())
            throw new AuthorizationException("L'utente non ha i permessi");

        return ((Commerciante) utente.getAzienda()).getCoalizione().getClienti();
    }
}
