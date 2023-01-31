package it.unicam.ids.studenti.ll.app.model.persistence.coalizione;

import it.unicam.ids.studenti.ll.app.model.Coalizione;
import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizionePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CoalizionePersistenceTest {

    @Autowired
    CoalizionePersistence persistence;

    @Test
    void getTest() {
        assertThrows(NoSuchElementException.class, () -> persistence.getCoalizione("1-1-1-1-1"));

        Coalizione c = persistence.getCoalizione("afc91eb5-263c-4211-a9ae-09f1d9db29c7");
        assert c.getId() != null;
        assert !Objects.equals(c.getId().toString(), "");
    }
}
