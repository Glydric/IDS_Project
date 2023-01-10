package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfficeTest {
    Azienda commerciante = new Commerciante("Sony",
            LocalDate.of(1970, 2, 6)
    );

    /*Dipendente d = Dipendente.Builder
            .getInstance()
            .addNome("Maguzzi")
            .addCognome("Maguzzo")
            .addDate(LocalDate.of(1990, 12, 5))
            .addCommerciante(commerciante)
            .build();*/

    Proprietario p = new Proprietario(
            "Mariooo",
            "Mazzolini",
            LocalDate.of(1990, 10, 25),
            commerciante
    );

    Office o = new FrontOffice(p.identificativo, "");

    @Test
    void addDipendente() {
        Persona p = new Persona(
                "Mocassini",
                "Luigini",
                LocalDate.of(1958, 2, 15)
        );

        assertThrows(AuthorizationException.class, () -> o.aggiungiDipendente(p));

//        o.allowDipendente(p, "aggiungiDipendente");
        assertDoesNotThrow(() -> o.aggiungiDipendente(p));
    }
}
