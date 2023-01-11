package it.unicam.ids.studenti.ll.model.ProgrammiFedelta;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PFBuilderTest {
    @Test
    void BuildCashback() {
        assertInstanceOf(ProgrammaCashback.class, ProgrammaFedelta.create("Cashback"));
    }

    @Test
    void BuildLivelli() {
        assertInstanceOf(ProgrammaLivelli.class, ProgrammaFedelta.create("Livelli"));
    }

    @Test
    void BuildPunti() {
        assertInstanceOf(ProgrammaPunti.class, ProgrammaFedelta.create("Punti"));
    }

    @Test
    void BuildVIP() {
        assertInstanceOf(ProgrammaVIP.class, ProgrammaFedelta.create("VIP"));
    }

    @Test
    void BuildReferral() {
        assertThrows(IllegalArgumentException.class, () -> ProgrammaFedelta.create("referral"));
        assertInstanceOf(ProgrammaReferral.class, ProgrammaFedelta
                .create()
                .setReferralFunction(() -> System.out.println("Referral function"))
                .setType("referral")
                .build());
    }

    @Test
    void BuildError() {
        assertThrows(IllegalArgumentException.class, () -> ProgrammaFedelta.create("qualcosa"));
    }
}