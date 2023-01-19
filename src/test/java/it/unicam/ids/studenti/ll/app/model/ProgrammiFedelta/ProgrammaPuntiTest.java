package it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProgrammaPuntiTest {

    @Test
    void ProgrammaPuntiCloneTest() {
        ProgrammaPunti pl = new ProgrammaPunti();
        ProgrammaPunti clone = pl.clone();

        pl.setPunti(10);

        assertNotEquals(pl.getPunti(), clone.getPunti());

    }
    // TODO
}
