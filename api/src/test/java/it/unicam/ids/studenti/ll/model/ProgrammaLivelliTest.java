package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProgrammaLivelliTest {
    @Test
    void ProgrammaLivelliCloneTest() {
        ProgrammaLivelli pl = new ProgrammaLivelli();
        ProgrammaLivelli clone = pl.clone();

        pl.setLivello((short) 10);

        assertNotEquals(pl.getLivello(), clone.getLivello());

    }
    //TODO add tests

}
