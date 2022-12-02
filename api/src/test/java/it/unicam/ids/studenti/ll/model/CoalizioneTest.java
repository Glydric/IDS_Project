package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoalizioneTest {
    @Test
    void addCliente() {
        Commerciante commerciante = new Commerciante("Xbox");
        Coalizione c = commerciante.gruppoAppartenza;

        Cliente c1 = new Cliente("Mario", "Draghi");

        c.addCliente(c1);
        assertTrue(c.getClienti().contains(c1));

        Cliente c2 = new Cliente("Mario", "Draghi");
        c.addCliente(c2);

        assertTrue(c.getClienti().contains(c1));
        assertTrue(c.getClienti().contains(c2));
        assertEquals(2, c.getClienti().size());
    }

    @Test
    void addProgrammaForEachClienteTest() {
        Commerciante commerciante = new Commerciante("Xbox");

        Cliente c1 = new Cliente("Mario", "Rossi");
        commerciante.addCliente(c1);

        ProgrammaPunti pl = new ProgrammaPunti();
        commerciante.addNewProgramma(pl);

        Cliente c2 = new Cliente("Mario", "Rossi");
        commerciante.addCliente(c2);

        ProgrammaCashback pc = new ProgrammaCashback();
        commerciante.addNewProgramma(pc);

        assert (commerciante.getProgress(c1).contains(pl));
        assert (commerciante.getProgress(c1).contains(pc));
        assert (commerciante.getProgress(c2).contains(pl));
        assert (commerciante.getProgress(c2).contains(pc));
    }

    @Test
    void getCommonProgramsTest() {
//        Coalizione c = new Coalizione();
//
//        assert (commerciante.listaProgrammi.contains(pf));
//        assert (commerciante.getClienti().contains(c1));
//        assert (commerciante.getClienti().contains(c2));
//
//        ((ProgrammaPunti) pf).setPunti(10);
//
//        assertNotEquals(commerciante.getProgress(c1).get(0), pf);
//        assertNotEquals(commerciante.getProgress(c2).get(0), pf);

    }

}
