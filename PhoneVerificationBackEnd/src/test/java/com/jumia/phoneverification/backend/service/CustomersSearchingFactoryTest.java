package com.jumia.phoneverification.backend.service;

import com.jumia.phoneverification.backend.service.customersearch.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomersSearchingFactoryTest {

    @Autowired
    CustomersSearchingFactory customersSearchingFactory;

    private static final boolean STATE = new Random().nextBoolean();
    private static final String COUNTRY = "Country";


    @Test
    @DisplayName("Test if trying to get Customers without filters")
    void getSearchingNoFilter() {
        CustomerSearch actual = customersSearchingFactory.getCustomerSearch(null, null);
        assertThat(actual).isInstanceOf(CustomerSearchAll.class);
    }

    @Test
    @DisplayName("Test if trying to get Customers filtered by state only")
    void getSearchingByStateOnly() {
        CustomerSearch actual = customersSearchingFactory.getCustomerSearch(STATE, null);
        assertThat(actual).isInstanceOf(CustomerSearchByState.class);
    }

    @Test
    @DisplayName("Test if trying to get Customers filtered by country only")
    void getSearchingByCountryOnly() {
        CustomerSearch actual = customersSearchingFactory.getCustomerSearch(null, COUNTRY);
        assertThat(actual).isInstanceOf(CustomerSearchByCountry.class);
    }

    @Test
    @DisplayName("Test if trying to get Customers filtered by country and state")
    void getSearchingByStateAndCountry() {
        CustomerSearch actual = customersSearchingFactory.getCustomerSearch(STATE, COUNTRY);
        assertThat(actual).isInstanceOf(CustomerSearchByStateAndCountry.class);
    }

}
