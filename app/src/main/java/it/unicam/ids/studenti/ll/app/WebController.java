package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class WebController {
    static final String loginParameters = "?userName=%s&password=%s";
    static final String button = """
            <button onclick="window.location.href='%s'">
                %s
            </button>
            """;
    static final String indexButton = String.format(button, "/?%s", "Home");
    static final String done = "<h1>Fatto</h1><br>" + indexButton;
    @GetMapping("/")
    public static String index(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password
    ) {
        if (userName == null) return "Inserire il nome utente";
        if (password == null) return "Inserire la password";

        return String.format(
                button,
                String.format(
                        Path.listaClienti + loginParameters,
                        userName,
                        password
                ),
                "listaClienti");
    }

    @GetMapping(Path.creaProprietarioAzienda)
    public static String creaProprietarioAzienda(
            @RequestParam(value = "nome") String nome,
            @RequestParam(value = "cognome") String cognome,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "anno") int anno,
            @RequestParam(value = "mese") int mese,
            @RequestParam(value = "giorno") int giorno,
            @RequestParam(value = "ragioneSociale") String nomeAzienda,
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
                            nomeAzienda,
                            date
                    )
            );
            if (password != null) p.setPassword(password);
            return String.format(
                    done + "<br>" + (
                            password == null
                                    ? "Attenzione password non impostata!!"
                                    : "La password è stata impostata"
                    ),
                    String.format(
                            loginParameters,
                            p.identificativo,
                            password == null
                                    ? ""
                                    : password
                    )
            );

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    @GetMapping(Path.creaAzienda)
    public static String creaAzienda(
            @RequestParam(value = "ragioneSociale") String ragioneSociale,
            @RequestParam(value = "anno") int anno,
            @RequestParam(value = "mese") int mese,
            @RequestParam(value = "giorno") int giorno
    ) {
        try {
            // TODO aggiungi al DB una volta completato
            new Commerciante(ragioneSociale, LocalDate.of(anno, mese, giorno));
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return done;
    }

    @GetMapping(Path.listaClienti)
    public static String listaClienti(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        if (userName == null) return "Inserire il nome utente";
        if (password == null) return "Inserire la password";

        try {
            Office office = new Office(userName, password);
            return office.getListaClienti().toString();
        } catch (IllegalArgumentException e) {
            return "<h1>Id o password Errati!!!</h1>";
        } catch (AuthorizationException e) {
            return "<h1>Chiedi i permessi ad un tuo superiore!!!</h1>";
        }

    }

    static abstract class Path {
        static final String listaClienti = "/lista/clienti";
        static final String creaProprietarioAzienda = "/crea/proprietarioAzienda";
        static final String creaAzienda = "/crea/azienda";
    }
}
