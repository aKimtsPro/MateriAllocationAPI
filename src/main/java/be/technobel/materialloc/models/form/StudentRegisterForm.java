package be.technobel.materialloc.models.form;

import be.technobel.materialloc.models.entity.Address;
import be.technobel.materialloc.models.entity.RegisterRequest;
import be.technobel.materialloc.models.entity.users.Student;
import lombok.Data;

@Data
public class StudentRegisterForm {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private StudentAddress address;

    public RegisterRequest toEntity(){
        RegisterRequest student = new RegisterRequest();

        student.setFirstName(firstname);
        student.setLastName(lastname);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAddress( address.toEntity() );

        return student;
    }

    @Data
    public static class StudentAddress {
        private String street;
        private int number;
        private String boxNumber;
        private int postalCode;
        private String city;
        private String country;
        private String region;

        private Address toEntity(){
            Address address1 = new Address();

            address1.setCity(city);
            address1.setRegion(region);
            address1.setNumber(number);
            address1.setCountry(country);
            address1.setStreet(street);
            address1.setBoxNumber(boxNumber);
            address1.setPostalCode(postalCode);

            return address1;
        }
    }

}
