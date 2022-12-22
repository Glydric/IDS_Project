/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AziendaTest {
    @Test
    void setEmailTest() {
        Azienda az = new Commerciante("Unicum & CO.");
        for (String email : List.of("dasd@ ds", "sfd@ awd", "ssajk@. ds", "ssajk@ . ds", "@ksjf.ds"))
            assertThrows(IllegalArgumentException.class, () -> az.setEmail(email));
        az.setEmail("antonio.gassner@gmail.com");
    }

    @Test
    void setNumeroTelefono() {
        Azienda az = new Commerciante("Unicum & CO.");
        assertThrows(IllegalArgumentException.class, () -> az.setNumeroTelefono(""));
        assertThrows(IllegalArgumentException.class, () -> az.setNumeroTelefono("231"));
        az.setNumeroTelefono("8493785092");
    }
}

