package it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProgrammaVIPTest {

    @Test
    void ProgrammaVIPCloneTest() {
        ProgrammaVIP pl = new ProgrammaVIP(false);
        ProgrammaVIP clone = pl.clone();

        pl.isVip = true;

        assertNotEquals(pl.isVip, clone.isVip);

    }
    //TODO

}
