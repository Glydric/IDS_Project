package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Identificatore;
import it.unicam.ids.studenti.ll.model.Persona;
import it.unicam.ids.studenti.ll.model.UtenteIdentificabile;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrontOfficeTest {
    @Test
    void loginProprietarioTest() {
        Commerciante c = new Commerciante("xbox", LocalDate.MIN);
        c.addDipendente(
                new Persona(
                        "Mario",
                        "Giulli",
                        LocalDate.of(2000, 12, 4)
                )
        );
        UtenteIdentificabile uid = Identificatore.getUtenteFrom("Mario.Giulli");
        uid.setPassword("bimariomaria");

        assertThrows(
                IllegalArgumentException.class,
                () -> new FrontOffice(uid.identificativo, "wrongPassword")
        );
        new FrontOffice(uid.identificativo, "bimariomaria");
    }
}
