package it.unicam.ids.studenti.ll.app.model.entity;

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
@Table(name = "coalizione")
public class CoalizioneEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

//    @JoinColumn(name = "id_commerciante", referencedColumnName = "id")
//    @OneToMany(cascade = CascadeType.PERSIST)
//    @Fetch(FetchMode.SELECT)
//    private CommercianteEntity appartenenti;
    // todo Map<Cliente, Set<ProgrammaFedelta>>


}
