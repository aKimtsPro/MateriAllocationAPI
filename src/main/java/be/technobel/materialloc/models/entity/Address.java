package be.technobel.materialloc.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter @Setter
public class Address implements Serializable {

    @Column(name = "address_street", nullable = false)
    private String street;
    @Column(name = "address_number", nullable = false)
    private int number;
    @Column(name = "address_box_number")
    private String boxNumber;
    @Column(name = "address_postal_code", nullable = false)
    private int postalCode;
    @Column(name = "address_city", nullable = false)
    private String city;
    @Column(name = "address_country", nullable = false)
    private String country;
    @Column(name = "address_region", nullable = false)
    private String region;

}
