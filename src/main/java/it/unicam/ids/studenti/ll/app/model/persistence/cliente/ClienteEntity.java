package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizioneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity implements Serializable {

    @Column(name = "nome")
    public String nome;
    @Column(name = "cognome")
    public String cognome;
    @Column(name = "data_nascita")
    public LocalDate dataNascita;
    @Column(name = "is_family")
    public boolean isFamily = false;
    @Id
    // @GeneratedValue(generator = "UUID")
    // @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "identificativo_tessera")
    private UUID identificativoTessera;
    @Column(name = "numero_telefono")
    private String numeroTelefono;
    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

//    @ManyToMany(mappedBy = "clienti", cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SELECT)
//    private Set<CoalizioneEntity> coalizioni;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "clienti_coalizioni",
            joinColumns = @JoinColumn(
                    name = "cliente_id",
                    referencedColumnName = "identificativo_tessera",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "coalizione_id",
                    referencedColumnName = "id",
                    nullable = false
            )
    )
    @Fetch(FetchMode.SELECT)
    private Set<CoalizioneEntity> coalizioni;
}
