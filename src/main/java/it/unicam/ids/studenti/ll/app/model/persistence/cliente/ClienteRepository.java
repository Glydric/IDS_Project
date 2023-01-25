package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import it.unicam.ids.studenti.ll.app.model.persistence.cliente.ClienteEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository <ClienteEntity, String> {

}
