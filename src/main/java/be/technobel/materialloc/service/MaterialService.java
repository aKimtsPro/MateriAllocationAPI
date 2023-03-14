package be.technobel.materialloc.service;

import be.technobel.materialloc.models.dto.MaterialDTO;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface MaterialService {

    @PreAuthorize("authenticated()")
    List<MaterialDTO> getAll();

}
