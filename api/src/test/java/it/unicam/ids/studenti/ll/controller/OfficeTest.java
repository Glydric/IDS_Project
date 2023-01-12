package it.unicam.ids.studenti.ll.controller;

import it.unicam.ids.studenti.ll.model.*;
import it.unicam.ids.studenti.ll.model.ProgrammiFedelta.ProgrammaFedelta;
import it.unicam.ids.studenti.ll.model.ProgrammiFedelta.ProgrammaPunti;
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

        Office frontOffice = new Office(d.identificativo, "");
        Office backOffice = new Office(proprietario.identificativo, "");

        Persona p2 = new Persona("Macciccio", "Luigone", LocalDate.of(1958, 2, 15));

        assertThrows(AuthorizationException.class, () -> frontOffice.aggiungiDipendente(p2));

        backOffice.allowDipendente(d, "aggiungiDipendente");

        assertDoesNotThrow(() -> frontOffice.aggiungiDipendente(p2));
    }
    @Test
    void inserimentoVenditaTest() throws AuthorizationException {
        Cliente cliente = new Cliente("Pippo","Baudo",LocalDate.of(1936,6,7),"3426417897","pippo@baudo.com");
        Office ufficio2 = new Office(proprietario.identificativo, "");
        ((Commerciante)ufficio2.utente.getAzienda()).addNewProgramma(ProgrammaFedelta.create("punti"));
        ufficio2.aggiungiCliente(cliente);
        ufficio2.inserimentoVendita(cliente,10);
        assert ((ProgrammaPunti)((Commerciante)ufficio2.utente.getAzienda()).getCoalizione().getProgrammi(cliente).stream().toList().get(0)).getPunti() == 10;

    }
}
