package com.jumia.phoneverification.backend.service.customersearch;

import com.jumia.phoneverification.backend.mapper.CustomerMapper;
import com.jumia.phoneverification.backend.model.dto.CustomerDtoPage;
import com.jumia.phoneverification.backend.model.entity.Customer;
import com.jumia.phoneverification.backend.repo.CustomerRepo;
import com.jumia.phoneverification.backend.service.CountryCodeService;
import com.jumia.phoneverification.backend.service.PhoneValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerSearchAll implements CustomerSearch {

    protected static final int DEFAULT_PAGE_SIZE = 10;

    protected CustomerRepo customerRepo;
    protected CustomerMapper customerMapper;
    protected PhoneValidation phoneValidation;
    protected CountryCodeService countryCodeService;

    public CustomerDtoPage getFilteredCustomer(int pageNumber, Boolean state, String country) {
        PageRequest pageRequest = PageRequest.of(pageNumber, DEFAULT_PAGE_SIZE);
        Page<Customer> all = customerRepo.findAll(pageRequest);
        return customerMapper.mapEntityPageToDto(all);
    }

    @Autowired
    public void setCustomerRepo(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Autowired
    public void setPhoneValidation(PhoneValidation phoneValidation) {
        this.phoneValidation = phoneValidation;
    }

    @Autowired
    public void setCountryCodeService(CountryCodeService countryCodeService) {
        this.countryCodeService = countryCodeService;
    }

}
