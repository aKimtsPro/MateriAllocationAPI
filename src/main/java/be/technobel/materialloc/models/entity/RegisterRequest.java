package be.technobel.materialloc.models.entity;

import be.technobel.materialloc.models.entity.users.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class RegisterRequest extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_id", nullable = false)
    private Long id;

    private LocalDateTime acceptedAt;
    @OneToOne
    private Student signedUp;

    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private Address address;

}
