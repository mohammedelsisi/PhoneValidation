package com.jumia.phoneverification.backend.repo;

import com.jumia.phoneverification.backend.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
}
