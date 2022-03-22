package com.jumia.phoneverification.backend.controller;

import com.jumia.phoneverification.backend.model.dto.CustomerDtoPage;
import com.jumia.phoneverification.backend.service.CustomersSearchingFactory;
import com.jumia.phoneverification.backend.service.customersearch.CustomerSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(value = "/customers")
public class CustomerResource {

    private final CustomersSearchingFactory customersSearchingFactory;

    @GetMapping
    public CustomerDtoPage getCustomers(@RequestParam int pageNumber, @RequestParam(required = false) Boolean state, @RequestParam(required = false) String country) {
        CustomerSearch customerSearch = customersSearchingFactory.getCustomerSearch(state, country);
        return customerSearch.getFilteredCustomer(pageNumber, state, country);
    }
}
