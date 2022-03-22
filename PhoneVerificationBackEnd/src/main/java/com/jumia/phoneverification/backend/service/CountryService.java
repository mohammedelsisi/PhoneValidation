package com.jumia.phoneverification.backend.service;

import com.jumia.phoneverification.backend.mapper.CustomerMapper;
import com.jumia.phoneverification.backend.model.entity.Customer;
import com.jumia.phoneverification.backend.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public Set<String> getCountries() {
        List<Customer> all = customerRepo.findAll();
        return customerMapper.getCountriesFromCustomers(all);
    }

}
