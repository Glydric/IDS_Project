package it.unicam.ids.studenti.ll.app;

import it.unicam.ids.studenti.ll.model.Commerciante;
import it.unicam.ids.studenti.ll.model.Proprietario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class BackofficeTest {
    @Test
    void loginProprietarioTest() {
        Proprietario p = new Proprietario("Mario", "Bianchini", LocalDate.now());
        p.setPassword("bimario");
        Commerciante c = new Commerciante("xbox", LocalDate.MIN, p);

        Backoffice b = new Backoffice(p.identificativo, "bimario");

    }
}
