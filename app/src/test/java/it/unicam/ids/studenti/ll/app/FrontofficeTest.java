package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Dipendente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrontofficeTest {
    @Test
    void loginProprietarioTest() {
        Dipendente d = new Dipendente(
                "Mario",
                "Giulli",
                LocalDate.MIN,
                new Commerciante("xbox", LocalDate.MIN));

        d.setPassword("bimario");

        assertThrows(
                IllegalArgumentException.class,
                () -> new Frontoffice(d.identificativo, "wrongPassword")
        );
        new Frontoffice(d.identificativo, "bimario");

    }
}
