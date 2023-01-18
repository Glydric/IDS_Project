package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
class WebController {

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

    @PostMapping(WebPaths.creaProprietarioAzienda)
    public static String creaProprietarioAzienda(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "cognome") String cognome,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "anno") int anno,
            @RequestParam(value = "mese") int mese,
            @RequestParam(value = "giorno") int giorno,
            @RequestParam(value = "annoAzienda", required = false) Integer annoAzienda,
            @RequestParam(value = "meseAzienda", required = false) Integer meseAzienda,
            @RequestParam(value = "giornoAzienda", required = false) Integer giornoAzienda
    ) {
        try {
            LocalDate date =
                    annoAzienda == null || meseAzienda == null || giornoAzienda == null
                            ? LocalDate.now()
                            : LocalDate.of(annoAzienda, meseAzienda, giornoAzienda);
            Proprietario p = new Proprietario(
                    nome,
                    cognome,
                    LocalDate.of(anno, mese, giorno),
                    new Commerciante(
                            ragioneSociale,
                            date
                    )
            );

            new Register(p.getAzienda());

            if (password != null) p.setPassword(password);

            return String.format(
                    WebContents.ok + "<br>" + (
                            password == null
                                    ? "Attenzione password non impostata!!"
                                    : "La password è stata impostata"
                    ),
                    WebContents.loginParameters(
                            p.identificativo.toString(),
                            password == null
                                    ? ""
                                    : password
                    )
            );

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

//    @PostMapping(WebPaths.creaAzienda)
//    public static String creaAzienda(
//            @RequestParam(value = "ragioneSociale") String ragioneSociale,
//            @RequestParam(value = "anno") int anno,
//            @RequestParam(value = "mese") int mese,
//            @RequestParam(value = "giorno") int giorno
//    ) {
//        try {
//            // TODO aggiungi al DB una volta completato
//            new Commerciante(ragioneSociale, LocalDate.of(anno, mese, giorno));
//        } catch (IllegalArgumentException e) {
//            return e.getMessage();
//        }
//        return WebContents.ok;
//    }

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
            return "<h1>userName o password Errati!!!</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }

    }

    @PostMapping
    public static String registraCliente(
            @PathVariable String azienda,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "cognome") String cognome,
            @RequestParam(value = "anno") int anno,
            @RequestParam(value = "mese") int mese,
            @RequestParam(value = "giorno") int giorno,
            @RequestParam(value = "nome") String numeroTelefono,
            @RequestParam(value = "nome") String email,
            @RequestParam(value = "isFamily", required = false) boolean isFamily
    ) {
        Cliente c = new Cliente(
                nome,
                cognome,
                LocalDate.of(anno, mese, giorno),
                numeroTelefono,
                email,
                isFamily
        );

        try {
            OfficeController
                    .authenticatedByRegisterOf(azienda)
                    .aggiungiCliente(c);
        } catch (IllegalArgumentException e) {
            return "<h1>userName o password Errati!!!</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }

    @PostMapping(WebPaths.aggiungiCliente)
    public static String aggiungiCliente(
            @PathVariable String ragioneSociale,
            @RequestParam(value = "tessera") String tessera
    ) {
        // usa la stringa azienda per ottenere la struttura dati dal db (attualmente Identificatore)
        OfficeController
                .authenticatedByRegisterOf(ragioneSociale)
//        .aggiungiCliente(ManagerDataBase.getClienteFromTessera(tessera))
        ;
        return WebContents.ok;
    }

    @PostMapping(WebPaths.aggiungiDipendente)
    public static String aggiungiDipendente(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "cognome") String cognome,
            @RequestParam(value = "anno") int anno,
            @RequestParam(value = "mese") int mese,
            @RequestParam(value = "giorno") int giorno,
            @RequestParam(value = "passwordDipendente", required = false) String passwordDipendente
    ) {
        try {
            OfficeController
                    .authenticatedBy(userName, password)
                    .aggiungiDipendente(
                            new Persona(
                                    nome,
                                    cognome,
                                    LocalDate.of(
                                            anno,
                                            mese,
                                            giorno
                                    )
                            ),
                            passwordDipendente
                    );
//            if (passwordDipendente != null)
//                Identificatore
//                        .getUtenteFrom(nome + '.' + cognome)
//                        .setPassword(passwordDipendente);
        } catch (IllegalArgumentException e) {
            return "<h1>"+e.getMessage()+"</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }
        return WebContents.ok;
    }
}
