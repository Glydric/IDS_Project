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
    Dipendente d1 = new Dipendente(
            "Maguzzi",
            "Maguzzo",
            LocalDate.of(1990, 12, 5),
            commerciante
    );

    Dipendente d2 = new Dipendente(
            "Mocassini",
            "Luigini",
            LocalDate.of(1958, 2, 15),
            commerciante
    );

    Office o = new FrontOffice(d1.identificativo, "");

    @Test
    void addDipendente() throws AuthorizationException {
        assertThrows(AuthorizationException.class, () -> o.aggiungiDipendente(d2));

        assert !o.utente.getAzienda().mapDipendenti.contains(d2);

        o.allowDipendente(d1,"aggiungiDipendente");
        assertDoesNotThrow(() -> o.aggiungiDipendente(d2));
    }
}
