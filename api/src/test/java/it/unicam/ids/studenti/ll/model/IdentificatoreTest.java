package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IdentificatoreTest {
    @Test
    void IDtest() {
        assert (Identificatore.isAvailable("mariorossi"));

        Identificatore d = Identificatore.fromString("mariorossi");
        assertEquals("mariorossi", d.getIdentificativo());

        assertThrows(
                IllegalArgumentException.class,
                () -> Identificatore.fromString("mariorossi")
        );
    }
}
