package be.technobel.materialloc.repository;

import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("""
        SELECT r
        FROM Request r
        WHERE r.date >= now() AND r.currentStatus = :status
        """)
    List<Request> findFutureWithCurrentStatus(RequestStatus status);

    @Query("""
        SELECT r
        FROM Request r
        WHERE
            r.date < :limitDate AND
            r.currentStatus != 'ACCEPTED' AND
            r.currentStatus != 'REFUSED'
    """)
    List<Request> findToCleanBefore(LocalDate limitDate);

}
