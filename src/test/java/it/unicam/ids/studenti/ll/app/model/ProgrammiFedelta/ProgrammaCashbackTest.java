package it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

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

    @Test
    void setRuleTest() {
        ProgrammaCashback programmaCashback = new ProgrammaCashback();
        programmaCashback.setRule((p,f)->{
            p.addCashback(f/100);
        });
        programmaCashback.aggiornaProgramma(500);
        assert programmaCashback.getCashback()==5;
        programmaCashback.aggiornaProgramma(50);
        assert programmaCashback.getCashback()==5.5;
    }

}
