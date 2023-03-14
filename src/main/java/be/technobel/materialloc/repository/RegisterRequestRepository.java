package be.technobel.materialloc.repository;

import be.technobel.materialloc.models.entity.RegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRequestRepository extends JpaRepository<RegisterRequest, Long> {
}
