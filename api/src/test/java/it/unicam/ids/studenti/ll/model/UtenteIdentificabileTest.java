package it.unicam.ids.studenti.ll.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtenteIdentificabileTest {
    @Test
    public void passwordTest(){
        Dipendente tizio = new Dipendente("Nomex","Cognomex");
        tizio.setPassword("ASDFGx123");
        assert (tizio.isPasswordValid("ASDFGx123"));
        assert (!tizio.isPasswordValid("asdfg"));
        assertThrows (IllegalArgumentException.class, ()-> tizio.setPassword(""));
        assertThrows (IllegalArgumentException.class, ()-> tizio.setPassword("asKL86"));
    }
}





