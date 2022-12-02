package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoalizioneTest {
    @Test
    void getCommonProgramsTest() {
        Commerciante c1 = new Commerciante("Rolex");
        Commerciante c2 = new Commerciante("Fendy");

        ProgrammaLivelli pf1 = new ProgrammaLivelli();
        ProgrammaVIP pf2 = new ProgrammaVIP();

        c1.addNewProgramma(pf1);
        c2.addNewProgramma(pf2);

        c1.merge(c2);

        assertEquals(0, c1.getCoalizione().getCommonPrograms().size());

        pf1.setLivello((short) 20);
        c2.addNewProgramma(pf1);
        pf1.setLivello((short) 40);

        // TODO try to remove equals in programmiFedelta to use default equals and check memory position
        assertEquals(1, c1.getCoalizione().getCommonPrograms().size());

        c1.addNewProgramma(pf2);
        assertEquals(2, c1.getCoalizione().getCommonPrograms().size());

    }

    @Test
    void addCliente() {
        Commerciante commerciante = new Commerciante("Xbox");
        Coalizione c = commerciante.getCoalizione();

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

        ProgrammaPunti pl = new ProgrammaPunti(5);
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
    void mergeTest() {
        Commerciante c1 = new Commerciante("Rolex");
        Commerciante c2 = new Commerciante("BMW");

        Cliente cl1 = new Cliente("Mario", "Rossi");
        Cliente cl2 = new Cliente("Mario", "Draghi");
        Cliente cl3 = new Cliente("Mario", "Bianchi");

        c1.addCliente(cl1);
        c2.addCliente(cl2);

        ProgrammaFedelta pf1 = new ProgrammaPunti(10);
        ProgrammaFedelta pf2 = new ProgrammaLivelli();

        c1.addNewProgramma(pf1);
        c2.addNewProgramma(pf2);

        c1.merge(c2);

        assertEquals(c1.getCoalizione(), c2.getCoalizione());
        assertEquals(2, c1.getClienti().size());

        // controlliamo che l'inserimento in un commerciante corrisponda all'inserimento in tutti i commercianti
        c2.addCliente(cl3);

        assertTrue(c1.getClienti().contains(cl3));

        // I programmi scelti da un commerciante non devono inficiare problemi scelti dagli altri anche dopo il merge
        assertFalse(c2.getListaProgrammi().contains(pf1));

        //Tutti i programmi sono 2
        assertEquals(2, c1.getCoalizione().getAllPrograms().size());

        //I programmi scelti da due commercianti sono diversi (hanno classi diverse) quindi non sono in comune
        assertEquals(0, c1.getCoalizione().getCommonPrograms().size());
        //TODO  complete

    }


}
