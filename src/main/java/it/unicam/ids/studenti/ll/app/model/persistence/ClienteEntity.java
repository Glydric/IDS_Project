package it.unicam.ids.studenti.ll.app.model.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.validation.constraints.Email;
import java.io.Serializable;

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
    //private UUID id;


    @Column(name = "isFamily")
    public boolean isFamily = false;

    @Column(name = "numeroTelefono")
    private String numeroTelefono;

    @Email
    @Column(name = "email")
    private String email;


    @Column (name = "password")
    private String password;



}
