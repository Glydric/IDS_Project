package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonSMSTest {
    @Test
    void getGestore() {
        SingletonSMS g = SingletonSMS.getEntity();
        assertEquals(g, SingletonSMS.getEntity());
    }
}
