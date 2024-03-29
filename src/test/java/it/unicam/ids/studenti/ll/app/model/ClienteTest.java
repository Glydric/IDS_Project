/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.unicam.ids.studenti.ll.app.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ClienteTest {
    @Test
    void constructorTest() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("TizioFuturo", "Cajo", LocalDate.of(3000, 12, 31), "342156578", "asdzxu@gmail.com")); //data di nascita assurda, post data odierna
        assertThrows(IllegalArgumentException.class, () -> new Cliente("TizioVecchio", "Caio", LocalDate.of(1800, 12, 31), "456123798", "lkj@hotmail.it")); //data assurda, persona incredibilmente troppo anziana
        assertThrows(IllegalArgumentException.class, () -> new Cliente("TizioBimbo", "Sempronio", LocalDate.of(2022, 10, 31), "458797", "lkj@tiscali.it")); //data di nascita di un minorenne

        new Cliente(
                "pippo",
                "pasto",
                1979,
                11,
                25,
                "3392094785",
                "ciao@ciao.it",
                false
        );
    }


}


