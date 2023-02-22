package be.technobel.materialloc.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Room extends BaseEntity<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long id;

    @Column(name = "room_number", unique = true, nullable = false)
    private int number;

    @Column(nullable = false)
    private int capacity;

    @Column(name = "student_access", nullable = false)
    private boolean studentAccess = true;

    @ManyToMany
    private Set<Material> materials = new LinkedHashSet<>();

}
