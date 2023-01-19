package it.unicam.ids.studenti.ll.app.model;

import java.util.Set;

public class SingletonSMS {
    private static SingletonSMS entity;

    private SingletonSMS() {
    }

    public static SingletonSMS getEntity() {
        if (entity == null)
            entity = new SingletonSMS();
        return entity;
    }

    /**
     * @param clienti i clienti a cui inviare il messaggio
     */
    public void inviaMessaggio(Set<Cliente> clienti, String message) {
        clienti.forEach((c) -> inviaMessaggio(c, message));
    }

    /**
     * @param cliente il cliente a cui inviare il messaggio
     */
    public void inviaMessaggio(Cliente cliente, String message) {
        System.out.println("Messaggio " + message + " inviato a " + cliente);
    }

}