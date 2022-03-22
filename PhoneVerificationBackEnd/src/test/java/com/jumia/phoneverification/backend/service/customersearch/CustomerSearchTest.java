package com.jumia.phoneverification.backend.service.customersearch;

import com.jumia.phoneverification.backend.mapper.CustomerMapper;
import com.jumia.phoneverification.backend.model.dto.CustomerDtoPage;
import com.jumia.phoneverification.backend.model.entity.Customer;
import com.jumia.phoneverification.backend.repo.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class CustomerSearchTest {

    @Autowired
    CustomerSearch customerSearchByState;
    @Autowired
    CustomerSearch customerSearchAll;
    @Autowired
    CustomerSearch customerSearchByCountry;
    @Autowired
    CustomerSearch customerSearchByStateAndCountry;
    @MockBean
    CustomerRepo customerRepo;
    @Mock
    Page<Customer> customerPage;
    List<Customer> customerList;

    @BeforeEach
    void arrange() {
        Customer validCustomerFromMozambique = new Customer(0, "Nada Sofie", "(258) 847651504");
        Customer validCustomerFromMorocco = new Customer(1, "customer name", "(212) 698054317");
        Customer invalidCustomerFromMorocco = new Customer(2, "customer name", "(212) 6007989253");
        Customer invalidCustomerFromUganda = new Customer(3, "customer name", "(256) 7771031454");
        //Creating two valid and one invalid customers
        customerList = new ArrayList<>();
        customerList.add(validCustomerFromMozambique);
        customerList.add(validCustomerFromMorocco);
        customerList.add(invalidCustomerFromMorocco);
        customerList.add(invalidCustomerFromUganda);

        given(customerRepo.findAll(any(PageRequest.class))).willReturn(customerPage);
        given(customerRepo.findAll()).willReturn(customerList);
        given(customerPage.toList()).willReturn(customerList);
        given(customerPage.getTotalPages()).willReturn(1);
    }

    @Test
    @DisplayName("test searching without any filter")
    void testSearchAll() {
        CustomerDtoPage actualFilteredCustomer = customerSearchAll.getFilteredCustomer(0, null, null);
        assertThat(actualFilteredCustomer.getCustomerDtoList()).isNotEmpty().hasSize(4);
        assertEquals(1,actualFilteredCustomer.getNumberOfPages(),"Number of pages was not calculated right");
    }

    @Test
    @DisplayName("test searching while filtering by state only")
    void testSearchByState() {
        CustomerDtoPage actualFilteredCustomer = customerSearchByState.getFilteredCustomer(0, true, null);
        assertThat(actualFilteredCustomer.getCustomerDtoList()).isNotEmpty().hasSize(2);
    }

    @Test
    @DisplayName("test searching while filtering by country only")
    void testSearchByCountry() {
        CustomerDtoPage actualFilteredCustomer = customerSearchByCountry.getFilteredCustomer(0, null, "Mozambique");
        assertThat(actualFilteredCustomer.getCustomerDtoList()).isNotEmpty().hasSize(1);
    }

    @Test
    @DisplayName("test searching while filtering by country and state")
    void testSearchByCountryAndState() {
        CustomerDtoPage actualFilteredCustomer = customerSearchByStateAndCountry.getFilteredCustomer(0, true, "Uganda");
        assertThat(actualFilteredCustomer.getCustomerDtoList()).isEmpty();
    }

}
