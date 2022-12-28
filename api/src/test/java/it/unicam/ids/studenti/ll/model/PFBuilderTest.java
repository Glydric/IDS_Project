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
        assertThrows(IllegalArgumentException.class, () -> PFBuilder.buildProgramma("referral"));
        PFBuilder.setReferralFunction(() -> System.out.println("Referral function"));
        assert ProgrammaReferral.class == PFBuilder.buildProgramma("referral").getClass();
    }

    @Test
    void BuildError() {
        assertThrows(IllegalArgumentException.class, () -> PFBuilder.buildProgramma("qualcosa"));
    }
}