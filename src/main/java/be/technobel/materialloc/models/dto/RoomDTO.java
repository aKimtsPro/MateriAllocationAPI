package be.technobel.materialloc.models.dto;

import be.technobel.materialloc.models.entity.Material;
import be.technobel.materialloc.models.entity.Room;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class RoomDTO {

    private final Long id;
    private final int number;
    private final int capacity;
    private final boolean studentAccess;
    private final Set<MaterialDTO> materials;

    public static RoomDTO toDto(Room entity){
        if( entity == null )
            return null;

        return new RoomDTO(
                entity.getId(),
                entity.getNumber(),
                entity.getCapacity(),
                entity.isStudentAccess(),
                entity.getMaterials().stream()
                        .map( MaterialDTO::from )
                        .collect(Collectors.toSet())
        );
    }

}
