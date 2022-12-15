package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class IdentificatoreTest {
    @Test
    void updateTest() {
        Dipendente d = new Dipendente("gianny", "monti");
        new Dipendente("Maria", "monti");
        //TODO check lowercase instantiation of id

        assertThrows(
                IllegalArgumentException.class,
                () -> d.identificativo.updateIdentificativo("maria.monti")
        );
    }
}
