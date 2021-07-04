package com.paypal.bfs.test.employeeserv.entities;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @PrimaryKeyJoinColumn
    @Column(name = "employee_id")
    @NotNull
    private Integer id;
    @Column(name = "first_name")
    @NotNull
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    private String lastName;
    @Column(name = "date_of_birth")
    @NotNull
    private String dateOfBirth;
    @OneToOne(targetEntity=AddressEntity.class,cascade=CascadeType.ALL)
    @NotNull
    private AddressEntity addressEntity;

    public EmployeeEntity(Employee employee) {
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.dateOfBirth = employee.getDateOfBirth();
        AddressEntity addressEnt = new AddressEntity(employee.getAddress());
        this.addressEntity = addressEnt;
    }

}
