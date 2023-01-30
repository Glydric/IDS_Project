package it.unicam.ids.studenti.ll.app.model.persistence.commerciante;

import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizioneEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommercianteRepository extends CrudRepository <CommercianteEntity, String> {
    @Transactional
    @Modifying
    @Query("update CommercianteEntity c set c.linkEsterno = ?2, c.wantCoalize = ?3, c.coalizione = ?4 WHERE c.ragioneSociale = ?1")
    void update(String ragioneSociale, String linkEsterno, CommercianteEntity wantCoalize, CoalizioneEntity coalizione);
}
