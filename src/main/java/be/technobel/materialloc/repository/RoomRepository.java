package be.technobel.materialloc.repository;

import be.technobel.materialloc.models.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    // récupère les salles libre entre 2 instants,
    // d'une capacité plus grande que désirée,
    // possèdant le matériel souhaité
    // disponible pour etudiant

    // récupère les salles libre entre 2 instants,
    // d'une capacité plus grande que désirée
    // disponible pour prof

}
