package be.technobel.materialloc.service.impl;

import be.technobel.materialloc.exceptions.NotFoundException;
import be.technobel.materialloc.models.entity.RegisterRequest;
import be.technobel.materialloc.models.form.StudentRegisterForm;
import be.technobel.materialloc.repository.RegisterRequestRepository;
import be.technobel.materialloc.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final RegisterRequestRepository registerRequestRepository;

    public StudentServiceImpl(RegisterRequestRepository registerRequestRepository) {
        this.registerRequestRepository = registerRequestRepository;
    }

    @Override
    public void signUp(StudentRegisterForm form) {
        registerRequestRepository.save(form.toEntity());
    }

    @Override
    public void validateRegistration(Long registrationId) {

        RegisterRequest request = registerRequestRepository.findById(registrationId)
                .orElseThrow(() -> new NotFoundException(RegisterRequest.class, registrationId));

    }


}
