package it.unicam.ids.studenti.ll.app.model;

import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaFedelta;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaLivelli;
import it.unicam.ids.studenti.ll.app.model.ProgrammiFedelta.ProgrammaPunti;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CoalizioneTest {
    @Test
    void getAllProgramsTest() {
        Commerciante c1 = new Commerciante("Rolex");
        Commerciante c2 = new Commerciante("Fendy");

        ProgrammaLivelli pf1 = (ProgrammaLivelli) ProgrammaFedelta.create("Livelli");
        ProgrammaFedelta pf2 = ProgrammaFedelta.create("ProgrammaVIP");

        c1.addNewProgramma(pf1);
        c2.addNewProgramma(pf2);

        assertThrows(IllegalStateException.class, () -> c1.mergeGroups(c2));

        c2.mergeGroups(c1);

        assertEquals(2, c1.getCoalizione().getAllPrograms().size());

        pf1.setLivello((short) 20);
        c2.addNewProgramma(pf1);


        assertEquals(3, c1.getCoalizione().getAllPrograms().size());

        c1.addNewProgramma(pf2);

        //I 2 programmi vip non vengono considerati come diversi (equals) e quindi la somma è 3 e non 4
        assertEquals(3, c1.getCoalizione().getAllPrograms().size());

        // Il numero di programmi non cambia perchè il programma inserito ora è uguale ad uno già inserito
        assertThrows(IllegalArgumentException.class, () -> c1.addNewProgramma(pf1));


        assertEquals(3, c1.getCoalizione().getAllPrograms().size());

    }

    @Test
    void getCommonProgramsTypeTest() {
        Commerciante c1 = new Commerciante("Unicredit");
        Commerciante c2 = new Commerciante("Xbox");
        c1.addNewProgramma(ProgrammaFedelta.create().setType("ProgrammaPunti").buildWith(10));
        c1.addNewProgramma(ProgrammaFedelta.create("ProgrammaCashback"));

        c2.addNewProgramma(ProgrammaFedelta.create().setType("ProgrammaPunti").buildWith(10));

        assertThrows(IllegalStateException.class, () -> c1.mergeGroups(c2));

        c2.mergeGroups(c1);
        Set<Class<? extends ProgrammaFedelta>> programs = c1.getCoalizione().getCommonProgramsType();

        assert (programs.size() == 1);
        assert (programs.contains(ProgrammaPunti.class));

    }

    @Test
    void getCommonProgramsTest() {
        Commerciante c1 = new Commerciante("Rolex");
        Commerciante c2 = new Commerciante("Fendy");

        ProgrammaLivelli pf1 = (ProgrammaLivelli) ProgrammaFedelta.create("ProgrammaLivelli");
        ProgrammaFedelta pf2 = ProgrammaFedelta.create("ProgrammaVIP");

        c1.addNewProgramma(pf1);
        c2.addNewProgramma(pf2);

        assertThrows(IllegalStateException.class, () -> c1.mergeGroups(c2));

        c2.mergeGroups(c1);

        assertEquals(0, c1.getCoalizione().getCommonPrograms().size());

        pf1.setLivello((short) 20);
        c2.addNewProgramma(pf1);
        pf1.setLivello((short) 40);

        assertEquals(0, c1.getCoalizione().getCommonPrograms().size());

        // dato che ora uno dei programmi è in comune tra entrambi i commercianti (in quanto abbiamo entrambi i
        // programmi a livelli con un livello default di 0, esso diventa programma in comun
        ((ProgrammaLivelli) c2.getProgrammi()
                .stream()
                .filter(p -> p.getClass() == ProgrammaLivelli.class)
                .toList().get(0)).setLivello((short) 0);

//        Dato che HashSet non ha un ordinamento specifico non possiamo fidarci dei get, per cui il seguente codice non è
//        eseguibile correttamente
//        ((ProgrammaLivelli) c2.getListaProgrammi().get(1)).setLivello((short) 0);
        assertEquals(1, c1.getCoalizione().getCommonPrograms().size());

        c1.addNewProgramma(pf2);
        assertEquals(2, c1.getCoalizione().getCommonPrograms().size());

    }

    @Test
    void addCliente() {
        Commerciante commerciante = new Commerciante("Xbox");
        Coalizione c = commerciante.getCoalizione();
        ProgrammaFedelta pl = ProgrammaFedelta.create("ProgrammaVIP");

        Cliente c1 = new Cliente("Mario", "Draghi");

        c.addCliente(c1);
        assertTrue(c.getClienti().contains(c1));

        Cliente c2 = new Cliente("Mario", "Draghi");
        c.addCliente(c2);

        assertTrue(c.getClienti().contains(c1));
        assertTrue(c.getClienti().contains(c2));
        assertEquals(2, c.getClienti().size());

        commerciante.addNewProgramma(pl);

        assert (c1.haveProgramIn(commerciante, pl));
        assert (c2.haveProgramIn(commerciante, pl));
    }

    @Test
    void addProgrammaForEachClienteTest() {
        Commerciante commerciante = new Commerciante("Xbox");

        Cliente c1 = new Cliente("Mario", "Rossi");
        commerciante.getCoalizione().addCliente(c1);

        ProgrammaPunti punti = (ProgrammaPunti) ProgrammaFedelta
                .create()
                .setType("ProgrammaPunti")
                .buildWith(5);
        commerciante.addNewProgramma(punti);

        Cliente c2 = new Cliente("Mario", "Bianchi");
        commerciante.getCoalizione().addCliente(c2);

        ProgrammaFedelta vip = ProgrammaFedelta.create("VIP");
        commerciante.addNewProgramma(vip);

        punti.setPunti(10);

        //Problema non funziona il getCommons
        assert (c1.haveProgramIn(commerciante, punti));
        assert (c1.haveProgramIn(commerciante, vip));
        assert (c2.haveProgramIn(commerciante, punti));
        assert (c2.haveProgramIn(commerciante, vip));
    }

    @Test
    void mergeTest() {
        Commerciante c1 = new Commerciante("Rolex");
        Commerciante c2 = new Commerciante("BMW");

        Cliente cl1 = new Cliente("Mario", "Rossi");
        Cliente cl2 = new Cliente("Mario", "Draghi");
        Cliente cl3 = new Cliente("Mario", "Bianchi");

        c1.getCoalizione().addCliente(cl1);
        c2.getCoalizione().addCliente(cl2);

        ProgrammaFedelta pf1 = ProgrammaFedelta.create().setType("punti").buildWith(10);
        ProgrammaFedelta pf2 = ProgrammaFedelta.create("Programmalivelli");

        c1.addNewProgramma(pf1);
        c2.addNewProgramma(pf2);

        assertThrows(IllegalStateException.class, () -> c1.mergeGroups(c2));

        c2.mergeGroups(c1);

        assertEquals(c1.getCoalizione(), c2.getCoalizione());
        assertEquals(2, c1.getCoalizione().getListaClienti().size());

        // controlliamo che l'inserimento in un commerciante corrisponda all'inserimento in tutti i commercianti
        c2.getCoalizione().addCliente(cl3);

        assertTrue(c1.getCoalizione().getListaClienti().contains(cl3));

        // I programmi scelti da un commerciante non devono inficiare problemi scelti dagli altri anche dopo il merge
        assertFalse(c2.getListaProgrammi().contains(pf1));

        //Tutti i programmi sono 2
        assertEquals(2, c1.getCoalizione().getAllPrograms().size());

        //I programmi scelti da due commercianti sono diversi (hanno classi diverse) quindi non sono in comune
        assertEquals(0, c1.getCoalizione().getCommonPrograms().size());
    }


}
