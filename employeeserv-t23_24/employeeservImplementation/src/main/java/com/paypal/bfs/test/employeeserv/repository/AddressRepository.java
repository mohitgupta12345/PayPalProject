package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.entities.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
}
