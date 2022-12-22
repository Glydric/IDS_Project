package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PFBuilderTest {
    @Test
    void BuildCashback() {
        assert ProgrammaCashback.class == PFBuilder.buildProgramma("Cashback").getClass();
    }

    @Test
    void BuildLivelli() {
        assert ProgrammaLivelli.class == PFBuilder.buildProgramma("Livelli").getClass();
    }

    @Test
    void BuildPunti() {
        assert ProgrammaPunti.class == PFBuilder.buildProgramma("Punti").getClass();
    }

    @Test
    void BuildVIP() {
        assert ProgrammaVIP.class == PFBuilder.buildProgramma("VIP").getClass();
    }

    @Test
    void BuildReferral() {
        assertThrows(IllegalArgumentException.class, () -> PFBuilder.buildProgramma("Referral"));
    }
}