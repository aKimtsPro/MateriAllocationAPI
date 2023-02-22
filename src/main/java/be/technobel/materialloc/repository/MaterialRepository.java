package be.technobel.materialloc.repository;

import be.technobel.materialloc.models.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
