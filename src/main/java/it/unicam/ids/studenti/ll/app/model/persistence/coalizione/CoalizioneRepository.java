package it.unicam.ids.studenti.ll.app.model.persistence.coalizione;

import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizioneEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CoalizioneRepository extends CrudRepository <CoalizioneEntity, UUID> {

}
