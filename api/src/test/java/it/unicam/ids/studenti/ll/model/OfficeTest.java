package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfficeTest {
    static Azienda azienda;
    static Proprietario proprietario;

    @BeforeAll
    static void SetProprietario() {
        azienda = new Commerciante("Sony", LocalDate.of(1970, 2, 6));
        proprietario = new Proprietario(
                "Mariooo",
                "Mazzolini",
                LocalDate.of(1990, 10, 25),
                azienda);
    }

    @Test
    void permissionTest() throws AuthorizationException {
        Persona persona = new Persona("Mocassini", "Luigini", LocalDate.of(1958, 2, 15));

        azienda.addDipendente(persona);
        Dipendente d = (Dipendente) Identificatore.getUtenteFrom("Mocassini.Luigini");

        Office frontOffice = new Office(d.identificativo, "");

        Persona p2 = new Persona("Macciccio", "Luigone", LocalDate.of(1958, 2, 15));

        assertThrows(AuthorizationException.class, () -> frontOffice.aggiungiDipendente(p2));

        new Office(proprietario.identificativo, "").allowDipendente(d, "aggiungiDipendente");

        assertDoesNotThrow(() -> frontOffice.aggiungiDipendente(p2));
    }

    @Test
    void aggiungiDipendentePermissionTest() {
        Persona persona = new Persona("Morcone", "Luigini", LocalDate.of(1958, 2, 15));

        Office backOffice = new Office(proprietario.identificativo, "");

        assertDoesNotThrow(() -> backOffice.aggiungiDipendente(persona));
    }

    @Test
    void aggiungiClienteTest() {
        Cliente cliente = new Cliente(
                "Maranno",
                "Luigini",
                LocalDate.of(1958, 2, 15),
                "4489275849",
                "bella@pe.te"
        );

        Office backOffice = new Office(proprietario.identificativo, "");

        assertDoesNotThrow(() -> backOffice.aggiungiCliente(cliente));
    }
}
