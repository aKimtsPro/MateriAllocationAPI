package be.technobel.materialloc.models.entity.users;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {

    @Override
    public void prePersist() {
        super.prePersist();
        this.setRole("STUDENT");
    }

}
