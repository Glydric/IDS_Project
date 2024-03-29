package it.unicam.ids.studenti.ll.app.web;

import it.unicam.ids.studenti.ll.app.model.*;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;
import it.unicam.ids.studenti.ll.app.model.persistence.cliente.ClientePersistence;
import it.unicam.ids.studenti.ll.app.model.persistence.commerciante.CommerciantePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class WebController {
    @Autowired
    private CommerciantePersistence commerciantePersistence;
    @Autowired
    private ClientePersistence clientePersistence;

    @GetMapping("/")
    public String index(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
    ) {
        if (userName == null) return "Inserire il nome utente";
        if (password == null) return "Inserire la password";

        return WebContents.button(
                WebPaths.listaClienti + WebContents.loginParameters(
                        userName,
                        password
                ),
                "listaClienti");
    }

    @PostMapping(WebPaths.creaProprietario)
    public String creaProprietario(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "cognome") String cognome,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "anno") int anno,
            @RequestParam(value = "mese") int mese,
            @RequestParam(value = "giorno") int giorno
    ) {
        Commerciante actualC = commerciantePersistence.getCommerciante(ragioneSociale);
        if (actualC.haveProprietario())
            return "<h2>Proprietario già iscritto, solo il vecchio proprietario può definire un successore</h2>";
        try {
            Proprietario proprietario = new Proprietario(
                    nome,
                    cognome,
                    anno,
                    mese,
                    giorno,
                    actualC,
                    password
            );

            return String.format(
                    WebContents.ok + "<br>" + (
                            password == null
                                    ? "Attenzione password non impostata!!"
                                    : "La password è stata impostata"
                    ),
                    WebContents.loginParameters(
                            proprietario.identificativo.toString(),
                            password == null
                                    ? ""
                                    : password
                    )
            );

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @PostMapping(WebPaths.creaAzienda)
    public String creaAzienda(
            @RequestBody Commerciante commerciante
    ) {
        try {
            commerciantePersistence.addOrThrowsCommerciante(commerciante);
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "<h1>";
        }
        return WebContents.ok;
    }

    @GetMapping(WebPaths.listaClienti)
    public String listaClienti(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
    ) {
        if (userName == null) return "Inserire il nome utente";
        if (password == null) return "Inserire la password";

        try {
            return OfficeController
                    .authenticatedBy(userName, password)
                    .getListaClienti()
                    .toString();
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }

    }

    /**
     * registra un cliente non già esistente
     *
     * @param ragioneSociale la ragione sociale dell'azienda
     * @param cliente        il cliente da aggiungere
     */
    @PostMapping(WebPaths.registraCliente)
    public String registraCliente(
            @PathVariable String ragioneSociale,
            @RequestBody Cliente cliente
    ) {

        Commerciante c = commerciantePersistence
                .getCommerciante(ragioneSociale);
        try {
            OfficeController
                    .authenticatedByRegisterOf(c)
                    .aggiungiCliente(cliente);
        } catch (Exception e) {
            return "<h1>" + e.getMessage() + "</h1>";
        }
        clientePersistence.addCliente(cliente);
        commerciantePersistence.updateCommerciante(c);
        return WebContents.ok + "<br>tessera: " + cliente.identificativoTessera;
    }

    /**
     * registra un cliente già esistente nell'applicativo
     *
     * @param ragioneSociale la ragione sociale dell'azienda
     * @param tessera        il cliente da aggiungere
     */
    @PostMapping(WebPaths.aggiungiCliente)
    public String aggiungiCliente(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "tessera") String tessera
    ) {
        // usa la stringa azienda per ottenere la struttura dati dal db (attualmente Identificatore)
        try {
            OfficeController.authenticatedByRegisterOf(
                            commerciantePersistence
                                    .getCommerciante(ragioneSociale)
                    )
                    .aggiungiCliente(clientePersistence.getCliente(tessera));
        } catch (Exception e) {
            return "<h1>" + e.getMessage() + "</h1>";
        }
        return WebContents.ok;
    }

    @PostMapping(WebPaths.aggiungiDipendente)
    public String aggiungiDipendente(
            @RequestBody Persona persona,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "passwordDipendente", required = false) String passwordDipendente
    ) {
        try {
            OfficeController
                    .authenticatedBy(userName, password)
                    .aggiungiDipendente(
                            persona,
                            passwordDipendente
                    );
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok + "userName: " + persona.nome + "." + persona.cognome;
    }

    @PostMapping(WebPaths.consentiDipendente)
    public String consentiDipendente(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "userNameDipendente") String userNameDipendente,
            @RequestParam(value = "permesso") String permesso
    ) {
        try {
            OfficeController
                    .authenticatedBy(userName, password)
                    .allowDipendente(userNameDipendente, permesso);
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }

    @PostMapping(WebPaths.coalizzaAzienda)
    public String coalizzaCon(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "ragioneSociale") String ragioneSociale
    ) {
        OfficeController office;
        Commerciante otherCommerciente = commerciantePersistence.getCommerciante(ragioneSociale);
        try {
            office = OfficeController
                    .authenticatedBy(userName, password);
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        }

        try {
            office.coalizzaCon(otherCommerciente);
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        } catch (Exception e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } finally {
            commerciantePersistence.updateCommerciante(
                    office.getCommerciante()
            );
            commerciantePersistence.updateCommerciante(
                    otherCommerciente
            );
        }
        return WebContents.ok;
    }


    @PostMapping(WebPaths.getPostPrograms)
    public String aggiungiProgramma(
            @RequestBody ProgrammaFedelta pf,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
    ) {
        try {
            OfficeController
                    .authenticatedBy(userName, password)
                    .aggiungiProgramma(pf);
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }

    @GetMapping(WebPaths.getPostPrograms)
    public String ottieniProgrammi(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "tessera") String tessera
    ) {
        try {
            return OfficeController
                    .authenticatedBy(userName, password)
                    .getProgrammi(tessera)
                    .toString();
        } catch (IllegalArgumentException | NullPointerException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
    }

    @GetMapping(WebPaths.clienteGetProgrammi)
    public String mostraProgrammi(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "tessera") String tessera,
            @RequestParam(value = "password") String password
    ) {
        try {
            return OfficeController
                    .authenticatedByRegisterOf(
                            commerciantePersistence.getCommerciante(ragioneSociale)
                    )
                    .getProgrammiOf(tessera, password)
                    .toString();
        } catch (IllegalArgumentException | NullPointerException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
    }
}
