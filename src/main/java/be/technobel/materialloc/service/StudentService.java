package be.technobel.materialloc.service;

import be.technobel.materialloc.models.form.StudentRegisterForm;

public interface StudentService {

    void signUp(StudentRegisterForm form);
    void validateRegistration(Long registrationId);

}
