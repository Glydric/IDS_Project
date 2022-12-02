package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ProgrammiTest {
    @Test
    void ProgrammaLivelliClone(){
        ProgrammaLivelli pl = new ProgrammaLivelli();
        ProgrammaLivelli clone = (ProgrammaLivelli) pl.clone();

        pl.setLivello((short) 10);

        assertNotEquals(pl.getLivello(),clone.getLivello());

    }
}
