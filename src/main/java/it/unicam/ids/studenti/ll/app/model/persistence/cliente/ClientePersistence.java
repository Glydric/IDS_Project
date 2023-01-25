package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizionePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientePersistence {
    @Autowired
    private ClienteRepository clienteRepository;
}
