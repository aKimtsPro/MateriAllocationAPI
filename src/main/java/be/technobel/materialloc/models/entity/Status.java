package be.technobel.materialloc.models.entity;

import be.technobel.materialloc.models.entity.users.Admin;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "request_status")
@Getter @Setter
public class Status extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus status;

    @Column(name = "justification", nullable = false)
    private String justification;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(name = "changed_by_id")
    private Admin changedBy;

}
