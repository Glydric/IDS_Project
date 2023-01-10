package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Dipendente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrontOfficeTest {
    @Test
    void loginProprietarioTest() {
        Dipendente d = new Dipendente(
                "Mario",
                "Giulli",
                LocalDate.of(2000, 12, 4),
                new Commerciante("xbox", LocalDate.MIN));

        d.setPassword("bimariomaria");

        assertThrows(
                IllegalArgumentException.class,
                () -> new FrontOffice(d.identificativo, "wrongPassword")
        );
        new FrontOffice(d.identificativo, "bimariomaria");

    }
}
