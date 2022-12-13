package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IdentificatoreTest {
    @Test
    void IDtest() {
        assert (Identificatore.isAvailable("mariorossi"));

        Identificatore id = Identificatore.fromString("mariorossi");
        assertEquals("mariorossi", id.getIdentificativo());

        Identificatore id1 = Identificatore.fromString("mario.rossi");
        assertEquals("mario.rossi", id1.getIdentificativo());

        Identificatore id2 = Identificatore.fromString("Mario.Rossi");
        assertEquals("mario.rossi", id2.getIdentificativo());

        assertThrows(IllegalArgumentException.class,()->Identificatore.fromString("Mario Rossi"));

        assertThrows(
                IllegalArgumentException.class,
                () -> Identificatore.fromString("mariorossi")
        );
    }
}
