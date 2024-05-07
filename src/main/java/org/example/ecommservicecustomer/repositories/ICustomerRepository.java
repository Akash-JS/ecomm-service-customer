package org.example.ecommservicecustomer.repositories;

import org.example.ecommservicecustomer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,String> {
}
