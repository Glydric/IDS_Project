package it.unicam.ids.studenti.ll.app.model.persistence.commerciante;

import it.unicam.ids.studenti.ll.app.model.Coalizione;
import it.unicam.ids.studenti.ll.app.model.Commerciante;
import it.unicam.ids.studenti.ll.app.model.persistence.commerciante.CommerciantePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CommerciantePersistenceTest {
    @Autowired
    CommerciantePersistence persistence;

    @Test
    void getCoalizioneFromCommercianteTest(){
        assertThrows(NoSuchElementException.class, () -> persistence.getCommerciante(""));

        Commerciante c = persistence.getCommerciante("sony");
        assert c.getCoalizione().getId() != null;
        assert !Objects.equals(c.getCoalizione().getId().toString(), "");
    }
}
