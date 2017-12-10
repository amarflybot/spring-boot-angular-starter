package com.amar.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by amarendra on 04/12/17.
 */
@Entity
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String streetName;

    private String country;

    @OneToOne(mappedBy = "address")
    private Person person;

    public Address() {
    }

    public Address(final String streetName, final String country) {
        this.streetName = streetName;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }
}
