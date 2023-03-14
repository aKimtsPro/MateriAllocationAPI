package be.technobel.materialloc.models.dto;

import be.technobel.materialloc.models.entity.users.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable {

    private final Long id;
    private final String role;
    private final String firstName;
    private final String lastName;
    private final String email;


    public static PersonDTO toDto(User entity) {
        if( entity == null )
            return null;

        return new PersonDTO(
                entity.getId(),
                entity.getRole(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail()
        );
    }

}
