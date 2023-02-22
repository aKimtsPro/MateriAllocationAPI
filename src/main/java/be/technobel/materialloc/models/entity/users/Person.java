package be.technobel.materialloc.models.entity.users;


import be.technobel.materialloc.models.entity.Address;
import be.technobel.materialloc.models.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
@Getter @Setter
public class Person extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    private String role;

    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private Address address;

}
