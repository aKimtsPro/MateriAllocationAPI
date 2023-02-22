package be.technobel.materialloc.repository;

import be.technobel.materialloc.models.entity.users.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByLogin(String login);

}
