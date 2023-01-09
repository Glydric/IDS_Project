package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonDBTest {
    @Test
    void getGestore() {
        SingletonDB g = SingletonDB.getEntity();
        assertEquals(g, SingletonDB.getEntity());
    }
}
