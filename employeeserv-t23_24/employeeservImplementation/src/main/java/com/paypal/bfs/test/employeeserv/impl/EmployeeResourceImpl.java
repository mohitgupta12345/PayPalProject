package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entities.AddressEntity;
import com.paypal.bfs.test.employeeserv.entities.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.exception.PayPalCustomException;
import com.paypal.bfs.test.employeeserv.repository.AddressRepository;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.request.EmployeeCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(Integer.parseInt(id));
        Employee employee = new Employee();

        if(employeeEntityOptional.isPresent()) {
            EmployeeEntity employeeEntity = employeeEntityOptional.get();
            employee.setFirstName(employeeEntity.getFirstName());
            employee.setLastName(employeeEntity.getLastName());
            employee.setDateOfBirth(employeeEntity.getDateOfBirth());
            employee.setId(employeeEntity.getId());
            Address address = fetchAddress(employeeEntity);
            employee.setAddress(address);
        } else {
            throw new PayPalCustomException("Employee with the given id does not exist");
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> createEmployee(@Valid EmployeeCreationRequest req) {

        if(req.getFirstName() == null || req.getLastName() == null || req.getDateOfBirth() == null || req.getLine1() == null || req.getCity() == null || req.getState() == null || req.getCountry() == null || req.getZipCode() == null) {
            throw new PayPalCustomException("All Mandatory Fields are missing");
        }

        String firstName = req.getFirstName();
        String lastName = req.getLastName();
        String dateOfBirth = req.getDateOfBirth();
        Address address = createAddress(req);

        Employee employee = createEmployee(firstName, lastName, dateOfBirth, address);
        EmployeeEntity employeeEntity = new EmployeeEntity(employee);
        employeeRepository.save(employeeEntity);

        Map<String, String> res = new HashMap<>();
        res.put("messg", "Created Successfully");
        res.put("id", String.valueOf(employeeEntity.getId()));
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.CREATED);
    }

    private Address fetchAddress(EmployeeEntity employeeEntity) {
        AddressEntity addressEntity = employeeEntity.getAddressEntity();
        Address address = new Address();
        address.setLine1(addressEntity.getLine1());
        address.setLine2(addressEntity.getLine2());
        address.setCity(addressEntity.getCity());
        address.setState(addressEntity.getState());
        address.setZipCode(addressEntity.getZipCode());
        return address;
    }

    private Employee createEmployee(String firstName, String lastName, String dateOfBirth, Address address) {
        Employee emp = new Employee();
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setDateOfBirth(dateOfBirth);
        emp.setAddress(address);
        return emp;
    }

    private Address createAddress(EmployeeCreationRequest req) {
        Address address = new Address();
        address.setLine1(req.getLine1());
        address.setLine2(req.getLine2());
        address.setCity(req.getCity());
        address.setState(req.getState());
        address.setCountry(req.getCountry());
        address.setZipCode(req.getZipCode());
        return address;
    }

}
