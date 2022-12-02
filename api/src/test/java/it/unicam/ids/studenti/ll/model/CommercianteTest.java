package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommercianteTest {
    @Test
    void addProgramma() {
        Commerciante commerciante = new Commerciante("Xbox", new Date(Integer.MAX_VALUE));
        ProgrammaFedelta pf = new ProgrammaPunti();

        commerciante.addNewProgramma(pf);

        assert (commerciante.listaProgrammi.contains(pf));
    }

    @Test
    void programmaClientiProgrammiTest() {
        Commerciante commerciante = new Commerciante("Xbox", new Date(Integer.MAX_VALUE));

        Cliente c1 = new Cliente("Andrea", "Bianchi");
        commerciante.addCliente(c1);

        ProgrammaFedelta pf = new ProgrammaPunti();
        commerciante.addNewProgramma(pf);

        Cliente c2 = new Cliente("Luigi", "Bianchi");
        commerciante.addCliente(c2);


        assert (commerciante.listaProgrammi.contains(pf));
        assert (commerciante.getClienti().contains(c1));
        assert (commerciante.getClienti().contains(c2));

        ((ProgrammaPunti) pf).setPunti(10);

        assertNotEquals(commerciante.getProgress(c1).get(0), pf);
        assertNotEquals(commerciante.getProgress(c2).get(0), pf);

    }
    @Test
    void addProgrammaTest(){
        Commerciante commerciante = new Commerciante("Xbox", new Date(Integer.MAX_VALUE));

        ProgrammaFedelta pf = new ProgrammaPunti();
        commerciante.addNewProgramma(pf);

        assertNotEquals(pf, commerciante.listaProgrammi.get(0));
    }
}
