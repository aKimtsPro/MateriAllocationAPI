package be.technobel.materialloc.service;

import be.technobel.materialloc.models.dto.ReducedRequestDTO;
import be.technobel.materialloc.models.entity.RequestStatus;
import be.technobel.materialloc.models.form.RequestForm;

import java.util.List;

public interface RequestService {

    void create(RequestForm form);

    List<ReducedRequestDTO> getFutureWithStatus(RequestStatus status);

}
