package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Proprietario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BackOfficeTest {
    @Test
    void loginProprietarioTest() {

        Proprietario p = new Proprietario(
                "Mario",
                "Bianchini",
                LocalDate.of(2000,12,5),
                new Commerciante("xbox", LocalDate.MIN));
        p.setPassword("bimariomaria");

        assertThrows(IllegalArgumentException.class, () -> new BackOffice(p.identificativo, "wrongPassword"));
        new BackOffice(p.identificativo, "bimariomaria");

    }
}
