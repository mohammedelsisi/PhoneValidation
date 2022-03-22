package com.jumia.phoneverification.backend.service.customersearch;

import com.jumia.phoneverification.backend.model.dto.CustomerDtoPage;
import com.jumia.phoneverification.backend.model.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CustomerSearchByStateAndCountry extends CustomerSearchAll {

    @Override
    public CustomerDtoPage getFilteredCustomer(int pageNumber, Boolean state, String country) {
        List<Customer> customerListBeforePagination = customerRepo.findAll().stream()
                .filter(customer -> phoneValidation.isValidPhone(customer.getPhone()) == state)
                .filter(customer -> countryCodeService.getCountryNameByPhone(customer.getPhone()).equals(country))
                .collect(toList());
        return customerMapper.getCustomerDtoPage(customerListBeforePagination, pageNumber);
    }
}
