package it.unicam.ids.studenti.ll.app.web;

abstract class WebPaths {
    static final String creaProprietario = "/{ragioneSociale}/proprietarioAzienda";
    static final String aggiungiCliente = "/{ragioneSociale}/aggiungi/cliente";
    static final String registraCliente = "/{ragioneSociale}/registra/cliente";
    static final String clienteGetProgrammi = "/{ragioneSociale}/programmi";
    static final String coalizzaAzienda = "/coalizza/azienda";
    static final String creaAzienda = "/crea/azienda";
    static final String listaClienti = "/ottieni/clienti";
    static final String aggiungiDipendente = "/aggiungi/dipendente";
    static final String consentiDipendente = "/consenti/dipendente";
    static final String getPostPrograms = "/programs";
}

