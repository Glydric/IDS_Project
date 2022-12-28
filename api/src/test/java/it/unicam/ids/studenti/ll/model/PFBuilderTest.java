package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PFBuilderTest {
    @Test
    void BuildCashback() {
        assertSame(ProgrammaCashback.class, ProgrammaFedelta.create("Cashback").getClass());
    }

    @Test
    void BuildLivelli() {
        assertSame(ProgrammaLivelli.class, ProgrammaFedelta.create("Livelli").getClass());
    }

    @Test
    void BuildPunti() {
        assertSame(ProgrammaPunti.class, ProgrammaFedelta.create("Punti").getClass());
    }

    @Test
    void BuildVIP() {
        assertSame(ProgrammaVIP.class, ProgrammaFedelta.create("VIP").getClass());
    }

    @Test
    void BuildReferral() {
        assertThrows(IllegalArgumentException.class, () -> ProgrammaFedelta.create("referral"));
        assertSame(ProgrammaFedelta
                .create()
                .setReferralFunction(() -> System.out.println("Referral function"))
                .setType("referral")
                .build()
                .getClass(), ProgrammaReferral.class);
    }

    @Test
    void BuildError() {
        assertThrows(IllegalArgumentException.class, () -> ProgrammaFedelta.create("qualcosa"));
    }
}