package be.technobel.materialloc.models.entity.users;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends Person {

    @Override
    public void prePersist() {
        super.prePersist();
        this.setRole("TEACHER");
    }

}
