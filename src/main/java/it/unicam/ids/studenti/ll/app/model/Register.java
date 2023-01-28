package it.unicam.ids.studenti.ll.app.model;

/**
 * Il register viene chiamato ogni volta che abbiamo bisogno di un utente che possa fare operazioni alla portata di tutti nell'Azienda
 * Tipo la registrazione di un utente
 */
public class Register extends UtenteIdentificabile {
    private final Azienda azienda;

    protected Register(Azienda azienda) {
        super("register", azienda.ragioneSociale, azienda.dataIscrizioneRegistroImprese);
        this.azienda = azienda;
        addPermessi(
                "inserimentoVendita",
                "aggiungiCliente",
                "getProgrammiOf"
        );
    }

    public static Register from(Azienda azienda) {
        return new Register(azienda);
    }

    public static void initializeFrom(Azienda azienda) {
        from(azienda);
    }

    @Override
    public Azienda getAzienda() {
        return azienda;
    }
}
