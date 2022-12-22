package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

public class ProgrammaReferralTest {
    @Test
    void runnableUseCode() {
        ProgrammaPunti pf = new ProgrammaPunti();
        ProgrammaReferral referral = new ProgrammaReferral(() -> pf.addPunti(10));

        referral.useCode();
        assert (pf.getPunti() == 10);
        referral.useCode();
        assert (pf.getPunti() == 20);
    }
}
