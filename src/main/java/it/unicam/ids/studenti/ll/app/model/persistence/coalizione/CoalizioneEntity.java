package it.unicam.ids.studenti.ll.app.model.persistence.coalizione;

import it.unicam.ids.studenti.ll.app.model.persistence.cliente.ClienteEntity;
import it.unicam.ids.studenti.ll.app.model.persistence.commerciante.CommercianteEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "coalizione")
public class CoalizioneEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JoinColumn(name = "coalizione", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SELECT)
    private Set<CommercianteEntity> appartenenti;

    @JoinColumn(name = "coalizione", referencedColumnName = "id")
    @OneToMany(cascade = CascadeType.DETACH)
    @Fetch(FetchMode.SELECT)
    private Set<ClienteEntity> clienti;

}
