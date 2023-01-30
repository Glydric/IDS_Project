package it.unicam.ids.studenti.ll.app.model.persistence.cliente;

import it.unicam.ids.studenti.ll.app.model.persistence.coalizione.CoalizioneEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "cliente")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "identificativo_tessera")
    private UUID identificativoTessera;

    @Column(name = "nome")
    public String nome;

    @Column(name = "cognome")
    public String cognome;

    @Column(name = "data_nascita")
    public LocalDate dataNascita;

    @Column(name = "is_family")
    public boolean isFamily = false;

    @Column(name = "numero_telefono")
    private String numeroTelefono;

    @Email
    @Column(name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @JoinColumn(name = "coalizione", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SELECT)
    private CoalizioneEntity coalizione;
}
