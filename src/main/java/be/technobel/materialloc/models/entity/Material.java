package be.technobel.materialloc.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Material extends BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
}
