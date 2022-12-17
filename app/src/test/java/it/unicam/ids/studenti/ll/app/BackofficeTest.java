package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Proprietario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BackofficeTest {
    @Test
    void loginProprietarioTest() {
        Proprietario p = new Proprietario("Mario", "Bianchini", LocalDate.now());
        p.setPassword("bimario");
        Commerciante c = new Commerciante("xbox", LocalDate.MIN, p);

        assertThrows(IllegalArgumentException.class, () -> new Backoffice(p.identificativo, "wrongPassword"));
        Backoffice b = new Backoffice(p.identificativo, "bimario");

    }
}
