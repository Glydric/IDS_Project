package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MagazzinoTest {
    @Test
    void setQuantitaProdotto() {
        Magazzino m = new Magazzino();
        Prodotto p = new Prodotto();

        m.setQuantitaProdotto(p, 10);
        assertEquals(10.0f, m.getQuantitaProdotto(p));

        assertThrows(IllegalArgumentException.class, () -> m.setQuantitaProdotto(p, -10));
        assertThrows(IllegalArgumentException.class, () -> m.setQuantitaProdotto(null, 10));
    }

    @Test
    void testIncrementaProdotto() {
        Magazzino m = new Magazzino();
        Prodotto p = new Prodotto();

        m.incrementaProdotto(p);
        assertEquals(1.0f, m.getQuantitaProdotto(p));
        m.incrementaProdotto(p);
        assertEquals(2.0f, m.getQuantitaProdotto(p));
        m.incrementaProdotto(p);
        assertEquals(3.0f, m.getQuantitaProdotto(p));
    }
    @Test
    void testDecrementaProdotto() {
        Magazzino m = new Magazzino();
        Prodotto p = new Prodotto();
        m.setQuantitaProdotto(p, 10);

        m.decrementaProdotto(p);
        assertEquals(9, m.getQuantitaProdotto(p));
        m.decrementaProdotto(p);
        assertEquals(8, m.getQuantitaProdotto(p));
        m.decrementaProdotto(p);
        assertEquals(7, m.getQuantitaProdotto(p));
    }
}
