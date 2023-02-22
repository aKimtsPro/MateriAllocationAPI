package be.technobel.materialloc.models.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable {

    private final Long id;
    private final String role;
    private final String firstName;
    private final String lastName;
    private final String email;

}
