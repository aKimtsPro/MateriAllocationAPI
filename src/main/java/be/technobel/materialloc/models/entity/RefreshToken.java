package be.technobel.materialloc.models.entity;

import be.technobel.materialloc.models.entity.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class RefreshToken extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;
    @Column(name = "refresh_token", unique = true)
    private String refreshToken;
    @Column(name = "created-at")
    private LocalDateTime refreshTokenCreatedAt;
    @Column(name = "expires-at")
    private LocalDateTime expiresAt;
    @OneToOne
    @JoinColumn(name = "refresh_token_user_id", nullable = false)
    private User user;

}
