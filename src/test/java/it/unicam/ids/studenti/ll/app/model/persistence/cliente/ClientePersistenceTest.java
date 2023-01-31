package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import it.unicam.ids.studenti.ll.app.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
@SpringBootTest
class ClientePersistenceTest {

    @Autowired
    ClientePersistence persistence;

    @Test
    void addClienteTest(){
        Cliente cliente1 = new Cliente("Pippo","Pluto", 1990,12,18,"3428956123","Pippo@hotmail.com",false);
        cliente1.setPassword("hjgfdhfyjgjh");
        persistence.addCliente(cliente1);
        Cliente cliente2 = persistence.getCliente(cliente1.identificativoTessera.toString());
        assert cliente2.equals(cliente1);
    }

}














