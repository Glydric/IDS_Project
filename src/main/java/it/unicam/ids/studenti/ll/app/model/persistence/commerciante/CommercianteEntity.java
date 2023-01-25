package it.unicam.ids.studenti.ll.app.model.persistence.commerciante;

import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizioneEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "commerciante")

/**
 * We will not save listaProgrammi for time based reasons
 */
public class CommercianteEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @Column(name = "link_esterno")
    private String linkEsterno;

    @JoinColumn(name = "id_coalizione", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    private CoalizioneEntity gruppoAppartenza;


}