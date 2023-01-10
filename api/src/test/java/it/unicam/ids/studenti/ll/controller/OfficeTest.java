package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OfficeTest {
    @Test
    void addDipendente() {
        Azienda commerciante = new Commerciante("Sony",
                LocalDate.of(1970, 2, 6)
        );
        Proprietario p = new Proprietario("mario",
                "monti",
                LocalDate.of(2000, 5, 5),
                commerciante
        );
        Office o = new BackOffice(p.identificativo, "");
        Dipendente d = new Dipendente(
                "Maguzzi",
                "Maguzzo",
                LocalDate.of(1990, 12, 5),
                commerciante
        );
        try {
            o.aggiungiDipendente(d);
        } catch (AuthorizationException e) {
            throw new RuntimeException(e);
        }
    }
}
