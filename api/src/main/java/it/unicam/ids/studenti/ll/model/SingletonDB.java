package it.unicam.ids.studenti.ll.model;


import java.util.UUID;

public class SingletonDB {
    private static SingletonDB entity;

    private SingletonDB() {
    }

    public static SingletonDB getEntity() {
        if (entity == null)
            entity = new SingletonDB();
        return entity;
    }

    public void saveCliente(Cliente cliente) {
        // TODO - implement GestoreDB.aggiungiCliente
        throw new UnsupportedOperationException();
    }

    public Cliente getCliente(UUID id) {
        // TODO - implement GestoreDB.ottieniCliente
        throw new UnsupportedOperationException();
    }

    public void saveDipendente(Dipendente dipendente) {
        // TODO - implement GestoreDB.aggiungiDipendente
        throw new UnsupportedOperationException();
    }

    public Dipendente getDipendente(Identificatore id) {
        // TODO - implement GestoreDB.ottieniDipendente
        throw new UnsupportedOperationException();
    }

    public void saveProprietario(Proprietario proprietario) {
        // TODO - implement GestoreDB.aggiungiDipendente
        throw new UnsupportedOperationException();
    }

    public Proprietario getProprietario(Identificatore id) {
        // TODO - implement GestoreDB.ottieniDipendente
        throw new UnsupportedOperationException();
    }
}