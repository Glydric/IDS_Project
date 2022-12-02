package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CommercianteTest {
    @Test
    void addProgramma() {
        Commerciante commerciante = new Commerciante("Xbox");
        ProgrammaFedelta pf = new ProgrammaPunti();

        commerciante.addNewProgramma(pf);

        assert (commerciante.listaProgrammi.contains(pf));

        Cliente c1 = new Cliente("Pippo", "Baudo");
        Cliente c2 = new Cliente("Mario", "Baudo");

        commerciante.addCliente(c1);
        commerciante.addCliente(c2);

        assertEquals(1,commerciante.getCoalizione().getAllPrograms().size());
        assertNotEquals(commerciante.getProgress(c1).get(0), commerciante.getProgress(c2).get(0));
    }

    @Test
    void progressiClientiTest() {
        Commerciante commerciante = new Commerciante("Xbox", new Date(Integer.MAX_VALUE));

        Cliente c1 = new Cliente("Andrea", "Bianchi");
        commerciante.addCliente(c1);

        ProgrammaPunti pf = new ProgrammaPunti(10);
        commerciante.addNewProgramma(pf);

        Cliente c2 = new Cliente("Luigi", "Bianchi");
        commerciante.addCliente(c2);

        assert (commerciante.listaProgrammi.contains(pf));

        // Controlla l'esistenza del cliente
        assert (commerciante.getClienti().contains(c1));
        assert (commerciante.getClienti().contains(c2));

        /// Controlla che il programma sia stato inserito

        // la classe deve essere uguale
        assertEquals(commerciante.getProgress(c1).get(0).getClass(), ProgrammaPunti.class);
        // ma l'oggetto deve essere diverso
        assertNotEquals(commerciante.getProgress(c1).get(0), pf);

        // la classe deve essere uguale
        assertEquals(commerciante.getProgress(c2).get(0).getClass(), ProgrammaPunti.class);
        // ma l'oggetto deve essere diverso
        assertNotEquals(commerciante.getProgress(c2).get(0), pf);

    }

    @Test
    void setCoalizione() {
        Commerciante co = new Commerciante("Swift");
        Coalizione c = new Coalizione();

        assertEquals(co.getCoalizione(), co.setCoalizione(c));
    }
}
