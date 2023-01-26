package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
@SpringBootTest
class ClientePersistenceTest {

    @Autowired
    ClientePersistence cliente;

    @Test
    void addClienteTest(){
        ClienteEntity cliente1 = new ClienteEntity();
        cliente.addCliente(cliente1);
        Optional<ClienteEntity> cliente2 = cliente.getCliente(cliente1.getIdentificativoTessera().toString());
        assert cliente2.isPresent();
        assert cliente1.equals(cliente2.get());

    }

}














