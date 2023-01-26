package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import it.unicam.ids.studenti.ll.app.model.persistence.cliente.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ClienteRepository extends CrudRepository <ClienteEntity, UUID> {

}
