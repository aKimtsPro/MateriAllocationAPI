package be.technobel.materialloc.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
public abstract class BaseEntity<TID> {

    public abstract TID getId();

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "last_modified_at", insertable = false)
    private LocalDateTime lastModifiedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedAt = LocalDateTime.now();
    }

}
