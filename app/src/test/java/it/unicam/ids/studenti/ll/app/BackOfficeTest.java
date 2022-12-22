package it.unicam.ids.studenti.ll.app;

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
                LocalDate.now(),
                new Commerciante("xbox", LocalDate.MIN));
        p.setPassword("bimario");

        assertThrows(IllegalArgumentException.class, () -> new BackOffice(p.identificativo, "wrongPassword"));
        new BackOffice(p.identificativo, "bimario");

    }
}
