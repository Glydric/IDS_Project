package it.unicam.ids.studenti.ll.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "cliente")

public class ClienteEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "isFamily")
    public boolean isFamily = false;

    @Column(name = "numeroTelefono")
    private String numeroTelefono;

    @Column(name = "email")
    private String email;

    @Column (name = "password")
    private String password;



}
