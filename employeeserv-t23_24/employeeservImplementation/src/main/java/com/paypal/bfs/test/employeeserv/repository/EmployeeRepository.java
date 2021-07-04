package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {
}
