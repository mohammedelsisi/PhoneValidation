package com.jumia.phoneverification.backend.service;

import com.jumia.phoneverification.backend.service.customersearch.CustomerSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomersSearchingFactory {

    @Qualifier("customerSearchByState")
    private final CustomerSearch customerSearchByState;
    @Qualifier("customerSearchAll")
    private final CustomerSearch customerSearchAll;
    @Qualifier("customerSearchByCountry")
    private final CustomerSearch customerSearchByCountry;
    @Qualifier("customerSearchByStateAndCountry")
    private final CustomerSearch customerSearchByStateAndCountry;


    public CustomerSearch getCustomerSearch(Boolean state, String country) {
        if (state != null && country != null) {
            return customerSearchByStateAndCountry;
        } else if (state != null) {
            return customerSearchByState;
        } else if (country != null) {
            return customerSearchByCountry;
        } else {
            return customerSearchAll;
        }

    }
}
