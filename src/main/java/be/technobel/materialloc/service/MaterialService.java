package be.technobel.materialloc.service;

import be.technobel.materialloc.models.dto.MaterialDTO;

import java.util.List;

public interface MaterialService {

    List<MaterialDTO> getAll();

}
