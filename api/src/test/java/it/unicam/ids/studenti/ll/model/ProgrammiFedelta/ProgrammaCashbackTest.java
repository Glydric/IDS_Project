package it.unicam.ids.studenti.ll.model.ProgrammiFedelta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProgrammaCashbackTest {
    //TODO

    @Test
    void ProgrammaCashbackCloneTest() {
        ProgrammaCashback pl = new ProgrammaCashback();
        ProgrammaCashback clone = pl.clone();

        pl.addCashback((short) 10);

        assertNotEquals(pl.getCashback(), clone.getCashback());

    }
}
