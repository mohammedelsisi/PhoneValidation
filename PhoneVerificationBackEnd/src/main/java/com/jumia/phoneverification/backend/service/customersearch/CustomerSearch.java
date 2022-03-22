package com.jumia.phoneverification.backend.service.customersearch;

import com.jumia.phoneverification.backend.model.dto.CustomerDtoPage;

public interface CustomerSearch {
    CustomerDtoPage getFilteredCustomer(int pageNumber, Boolean state, String country);
}
