package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfficeTest {
    Azienda azienda = new Commerciante("Sony", LocalDate.of(1970, 2, 6));

    Proprietario proprietario = new Proprietario("Mariooo", "Mazzolini", LocalDate.of(1990, 10, 25), azienda);

    @Test
    void addDipendentePermissionTest() throws AuthorizationException {
        Persona persona = new Persona("Mocassini", "Luigini", LocalDate.of(1958, 2, 15));

        azienda.addDipendente(persona);
        Dipendente d = (Dipendente) Identificatore.getUtenteFrom("Mocassini.Luigini");

        Office front = new FrontOffice(d.identificativo, "");
        Office back = new BackOffice(proprietario.identificativo, "");

        Persona p2 = new Persona("Macciccio", "Luigone", LocalDate.of(1958, 2, 15));

        assertThrows(AuthorizationException.class, () -> front.aggiungiDipendente(p2));

        back.allowDipendente(d, "aggiungiDipendente");

        assertDoesNotThrow(() -> front.aggiungiDipendente(p2));
    }
}
