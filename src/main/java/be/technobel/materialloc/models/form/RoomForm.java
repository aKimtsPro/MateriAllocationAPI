package be.technobel.materialloc.models.form;

import be.technobel.materialloc.models.entity.Material;
import be.technobel.materialloc.models.entity.Request;
import be.technobel.materialloc.models.entity.Room;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class RoomForm {

    @Min(1)
    private int number;
    @Min(1)
    private int capacity;
    private boolean studentAccess;
    private Set<Long> materialsId;

    public Room toEntity(){
        Room room = new Room();
        room.setNumber(number);
        room.setCapacity(capacity);
        room.setStudentAccess(studentAccess);
        return room;
    }

}
