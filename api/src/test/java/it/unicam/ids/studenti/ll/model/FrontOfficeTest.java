package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrontOfficeTest {
    @Test
    void loginTest() {
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
                () -> new OfficeController(uid.identificativo, "wrongPassword")
        );
        new OfficeController(uid.identificativo, "bimariomaria");
    }


}
