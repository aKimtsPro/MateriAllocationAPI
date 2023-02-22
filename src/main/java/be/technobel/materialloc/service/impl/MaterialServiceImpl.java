package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.models.dto.MaterialDTO;
import be.technobel.materialloc.repository.MaterialRepository;
import be.technobel.materialloc.service.MaterialService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public List<MaterialDTO> getAll() {
        return materialRepository.findAll()
                .stream()
                .map(MaterialDTO::from)
                .toList();
    }

}
