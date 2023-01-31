package it.unicam.ids.studenti.ll.app.model;

import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaPunti;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CommercianteTest {
    @Test
    void addProgramma() {
        Commerciante commerciante = new Commerciante("Xbox");
        ProgrammaFedelta pf = ProgrammaFedelta.create("punti");

        commerciante.addNewProgramma(pf);

        assert (commerciante.getListaProgrammi().contains(pf));

        Cliente c1 = new Cliente("Pippo", "Baudo");
        Cliente c2 = new Cliente("Mario", "Baudo");

        commerciante.getCoalizione().addCliente(c1);
        commerciante.getCoalizione().addCliente(c2);


        ((ProgrammaPunti) c1
                .getProgressAsListIn(commerciante)
                .get(0)
        ).setPunti(10);

        assertEquals(1, commerciante.getCoalizione().getAllPrograms().size());
        assertNotEquals(
                c1.getProgressAsListIn(commerciante).get(0),
                c2.getProgressAsListIn(commerciante).get(0)
        );

        assertThrows(UnsupportedOperationException.class, () -> commerciante.getListaProgrammi().add(pf));
    }

    @Test
    void progressiClientiTest() {
        Commerciante commerciante = new Commerciante("Xbox");

        Cliente c1 = new Cliente("Andrea", "Bianchi");
        commerciante.getCoalizione().addCliente(c1);

        ProgrammaPunti pf = (ProgrammaPunti) ProgrammaFedelta.create().setType("Programmapunti").buildWith(10);
        commerciante.addNewProgramma(pf);

        Cliente c2 = new Cliente("Luigi", "Bianchi");
        commerciante.getCoalizione().addCliente(c2);

        assert (commerciante.getListaProgrammi().contains(pf));

        // Controlla l'esistenza del cliente
        assert (commerciante.getCoalizione().getListaClienti().contains(c1));
        assert (commerciante.getCoalizione().getListaClienti().contains(c2));

        /// Controlla che il programma sia stato inserito
        pf.setPunti(5); // modifichiamo i punti cosi da assicurarci che l'oggetto sia diverso durante i prossimi controlli

        // la classe deve essere uguale
        assertInstanceOf(ProgrammaPunti.class, c1.getProgressAsListIn(commerciante).get(0));
        // ma l'oggetto deve essere diverso
        assertNotEquals(c1.getProgressAsListIn(commerciante).get(0), pf);

        // la classe deve essere uguale
        assertInstanceOf(ProgrammaPunti.class, c2.getProgressAsListIn(commerciante).get(0));
        // ma l'oggetto deve essere diverso
        assertNotEquals(c2.getProgressAsListIn(commerciante).get(0), pf);

    }

    /**
     * controlla che il tipo di ritorno sia lo stesso e che non vi siano errori
     */
    @Test
    void setCoalizione() {
        Commerciante co = new Commerciante("Swift");
        Coalizione c = new Coalizione();

        assertEquals(co.getCoalizione(), co.setCoalizioneGettingOld(c));
    }

    @Test
    void addDipendente() {
        Commerciante c = new Commerciante("Sony");
        Persona d = new Persona("Gianny", "Manny", LocalDate.of(2000, 10, 10));

        c.addDipendente(d);
        assert (c.mapDipendenti.size() == 1);
        assertThrows(IllegalArgumentException.class, () -> c.addDipendente(d));
    }

    @Test
    void urlTest() {
        Commerciante c = new Commerciante("Frizzy");
        c.setLinkEsterno("https://googlemybusiness.co/frizzy");

        String[] array = {"http://Ciaooooooo", "OK", " ", "https://www.ciao.", "https://www.ciao. ", "https://www.gatto"};
        for (String link : array) {
            assertThrows(IllegalArgumentException.class, () -> c.setLinkEsterno(link), link + " not wrong");
        }
    }
}
