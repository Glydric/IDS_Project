package it.unicam.ids.studenti.ll.model;

/**
 * Il register viene chiamato ogni volta che abbiamo bisogno di un utente che possa fare operazioni alla portata di tutti nell'Azienda
 * Tipo la registrazione di un utente
 */
public class Register extends UtenteIdentificabile {
    private final Azienda azienda;

    public Register(Azienda azienda) {
        super("register", azienda.ragioneSociale);
        this.azienda = azienda;
        super.listaPermessi.add("inserimentoVendita");
        super.listaPermessi.add("aggiungiCliente");
    }

    @Override
    public Azienda getAzienda() {
        return azienda;
    }
}
