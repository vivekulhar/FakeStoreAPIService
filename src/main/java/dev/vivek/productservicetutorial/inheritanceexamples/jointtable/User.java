package dev.vivek.productservicetutorial.inheritanceexamples.jointtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "jt_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String password;

    /*@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "binary(16)",updatable = false, nullable = false)
    private UUID uuid;*/
}
