package it.unicam.ids.studenti.ll.app.model.persistence.commerciante;

import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizioneEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "commerciante")

/**
 * We will not save listaProgrammi for time based reasons
 */
public class CommercianteEntity implements Serializable {

    @Id
    @Column(name = "ragione_sociale")
    private String ragioneSociale;

    @Column(name = "link_esterno")
    private String linkEsterno;

    @Column(name = "data_iscrizione")
    private LocalDate dataIscrizione;

    @JoinColumn(name = "want_coalize", referencedColumnName = "ragione_sociale")
    @OneToOne(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    private CommercianteEntity wantCoalize;

    @JoinColumn(name = "coalizione", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    private CoalizioneEntity coalizione;
}
