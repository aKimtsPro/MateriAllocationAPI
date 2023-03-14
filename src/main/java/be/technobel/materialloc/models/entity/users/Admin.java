package be.technobel.materialloc.models.entity.users;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADMIN")
@Getter @Setter
public class Admin extends User {

    @Override
    public void prePersist() {
        super.prePersist();
        this.setRole("ADMIN");
    }

}
