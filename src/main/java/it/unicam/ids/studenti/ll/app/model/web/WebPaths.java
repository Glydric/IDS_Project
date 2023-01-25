package it.unicam.ids.studenti.ll.app.model.web;

abstract class WebPaths {
    static final String creaProprietarioAzienda = "/{ragioneSociale}/crea/proprietarioAzienda";
    static final String aggiungiCliente = "/{ragioneSociale}/aggiungi/cliente";
    static final String registraCliente = "/{ragioneSociale}/registra/cliente";


    static final String creaAzienda = "/crea/azienda";
    static final String listaClienti = "/ottieni/clienti";
    static final String aggiungiDipendente = "/aggiungi/dipendente";
}

