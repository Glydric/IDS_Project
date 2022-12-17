package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommercianteTest {
    @Test
    void addProgramma() {
        Commerciante commerciante = new Commerciante("Xbox");
        ProgrammaFedelta pf = new ProgrammaPunti();

        commerciante.addNewProgramma(pf);

        assert (commerciante.getListaProgrammi().contains(pf));

        Cliente c1 = new Cliente("Pippo", "Baudo");
        Cliente c2 = new Cliente("Mario", "Baudo");

        commerciante.addCliente(c1);
        commerciante.addCliente(c2);

        ((ProgrammaPunti) commerciante.getProgressAsList(c1).get(0)).setPunti(10);

        assertEquals(1, commerciante.getCoalizione().getAllPrograms().size());
        assertNotEquals(commerciante.getProgressAsList(c1).get(0), commerciante.getProgressAsList(c2).get(0));

        assertThrows(UnsupportedOperationException.class, () -> commerciante.getListaProgrammi().add(pf));
    }

    @Test
    void progressiClientiTest() {
        Commerciante commerciante = new Commerciante("Xbox");

        Cliente c1 = new Cliente("Andrea", "Bianchi");
        commerciante.addCliente(c1);

        ProgrammaPunti pf = new ProgrammaPunti(10);
        commerciante.addNewProgramma(pf);

        Cliente c2 = new Cliente("Luigi", "Bianchi");
        commerciante.addCliente(c2);

        assert (commerciante.getListaProgrammi().contains(pf));

        // Controlla l'esistenza del cliente
        assert (commerciante.getClienti().contains(c1));
        assert (commerciante.getClienti().contains(c2));

        /// Controlla che il programma sia stato inserito
        pf.setPunti(5); // modifichiamo i punti cosi da assicurarci che l'oggetto sia diverso durante i prossimi controlli

        // la classe deve essere uguale
        assertEquals(commerciante.getProgressAsList(c1).get(0).getClass(), ProgrammaPunti.class);
        // ma l'oggetto deve essere diverso
        assertNotEquals(commerciante.getProgressAsList(c1).get(0), pf);

        // la classe deve essere uguale
        assertEquals(commerciante.getProgressAsList(c2).get(0).getClass(), ProgrammaPunti.class);
        // ma l'oggetto deve essere diverso
        assertNotEquals(commerciante.getProgressAsList(c2).get(0), pf);

    }

    @Test
    void setCoalizione() {
        Commerciante co = new Commerciante("Swift");
        Coalizione c = new Coalizione();

        assertEquals(co.getCoalizione(), co.setCoalizione(c));
    }

    @Test
    void addDipendente() {
        Commerciante c = new Commerciante("Sony");
        Dipendente d = new Dipendente("Gianny", "Manny");

        c.addDipendente(d);
        assert (c.mapDipendenti.contains(d));
        assertThrows(IllegalArgumentException.class, () -> c.addDipendente(d));
    }
}
