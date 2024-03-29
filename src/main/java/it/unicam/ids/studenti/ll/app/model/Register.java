package it.unicam.ids.studenti.ll.app.model;

/**
 * Il register viene chiamato ogni volta che abbiamo bisogno di un utente che possa fare operazioni alla portata di tutti nell'Azienda
 * Tipo la registrazione di un utente
 */
public class Register extends UtenteIdentificabile {
    private final Azienda azienda;

    protected Register(Azienda azienda) {
        super("register", azienda.getRagioneSociale(), azienda.getDataIscrizione());
        this.azienda = azienda;
        addPermessi(
                "inserimentoVendita",
                "aggiungiCliente",
                "getProgrammiOf"
        );
    }

    public static Register initFrom(Azienda azienda) {
        String id = Identificatore.getFormat("register", azienda.getRagioneSociale());
        if (!Identificatore.isAvailable(id))
            return (Register) Identificatore.getUtenteFrom(id);
        else
            return new Register(azienda);
    }


    @Override
    public Azienda getAzienda() {
        return azienda;
    }
}
