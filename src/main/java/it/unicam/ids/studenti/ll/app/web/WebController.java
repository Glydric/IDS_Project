package it.unicam.ids.studenti.ll.app.web;

import it.unicam.ids.studenti.ll.app.model.*;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@RestController
class WebController {
    static Set<Commerciante> commercianti = new HashSet<>();

    @GetMapping("/")
    public static String index(
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
    public static String creaProprietario(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "cognome") String cognome,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "anno") int anno,
            @RequestParam(value = "mese") int mese,
            @RequestParam(value = "giorno") int giorno
    ) {
        Commerciante actualC =
                getCommercianteFrom(ragioneSociale);
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
    public static String creaAzienda(
            @RequestBody Commerciante commerciante
    ) {
        try {
            // TODO modificare la chiamata per usare il DB
            commercianti.add(commerciante);
            Register.initializeFrom(commerciante);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return WebContents.ok;
    }

    @GetMapping(WebPaths.listaClienti)
    public static String listaClienti(
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
    public static String registraCliente(
            @PathVariable String ragioneSociale,
            @RequestBody Cliente cliente
    ) {
        try {
            OfficeController
                    .authenticatedByRegisterOf(ragioneSociale)
                    .aggiungiCliente(cliente);
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }

    /**
     * registra un cliente già esistente nell'applicativo
     *
     * @param ragioneSociale la ragione sociale dell'azienda
     * @param tessera        il cliente da aggiungere
     */
    @PostMapping(WebPaths.aggiungiCliente)
    public static String aggiungiCliente(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "tessera") String tessera
    ) {
        // usa la stringa azienda per ottenere la struttura dati dal db (attualmente Identificatore)
        try {
            OfficeController
                    .authenticatedByRegisterOf(ragioneSociale)
            // TODO controlla che la tessera sia effettivamente esistente
            //        .aggiungiCliente(ManagerDataBase.getClienteFromTessera(tessera))
            ;
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }

    @PostMapping(WebPaths.aggiungiDipendente)
    public static String aggiungiDipendente(
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
        return WebContents.ok + "userName: " + persona.cognome + "." + persona.cognome;
    }

    @PostMapping(WebPaths.consentiDipendente)
    public static String consentiDipendente(
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
    public static String coalizzaCon(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "ragioneSociale") String ragioneSociale
    ) {
        try {
            OfficeController
                    .authenticatedBy(userName, password)
                    .coalizzaCon(getCommercianteFrom(ragioneSociale));
        } catch (IllegalStateException e) {
            return "Attendere che l'altra azienda accetti la richiesta";
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }

    @PostMapping(WebPaths.aggiungiProgramma)
    public static String aggiungiProgramma(
            @RequestBody ProgrammaFedelta pf,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
    ) {
        try {
            OfficeController
                    .authenticatedBy(userName,password)
                    .aggiungiProgramma(pf);
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }

    @GetMapping(WebPaths.programmi)
    public static String ottieniProgrammi(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "tessera") String tessera,
            @RequestParam(value = "password") String password
    ) {
        try {
            return OfficeController
                    .authenticatedByRegisterOf(ragioneSociale)
                    .getProgrammiOf(tessera, password)
                    .toString();
        } catch (IllegalArgumentException e) {
            return "<h1>" + e.getMessage() + "</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
    }

    static Commerciante getCommercianteFrom(String ragioneSociale) {
        List<Commerciante> c = commercianti.stream().filter(
                commerciante -> Objects.equals(commerciante.ragioneSociale, ragioneSociale)
        ).toList();
        assert c.size() == 1;
        return c.get(0);
    }
}
