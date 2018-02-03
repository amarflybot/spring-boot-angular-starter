package com.amar.app.model;

import javax.persistence.*;

/**
 * Created by amarendra on 04/12/17.
 */
@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Address address;

    public Person() {
    }

    public Person(final String name, final String password) {
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
