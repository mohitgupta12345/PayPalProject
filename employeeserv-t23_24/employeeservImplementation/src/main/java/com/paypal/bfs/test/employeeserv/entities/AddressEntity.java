package com.paypal.bfs.test.employeeserv.entities;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@Data
public class AddressEntity {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    @Column(name = "line1")
    @NotNull
    private String line1;
    @Column(name = "line2")
    private String line2;
    @Column(name = "city")
    @NotNull
    private String city;
    @Column(name = "state")
    @NotNull
    private String state;
    @Column(name = "country")
    @NotNull
    private String country;
    @Column(name = "zip_code")
    @NotNull
    private String zipCode;

    public AddressEntity(Address address) {
        this.line1 = address.getLine1();
        this.line2 = address.getLine2();
        this.city = address.getCity();
        this.state = address.getState();
        this.country = address.getCountry();
        this.zipCode = address.getZipCode();
    }
}
