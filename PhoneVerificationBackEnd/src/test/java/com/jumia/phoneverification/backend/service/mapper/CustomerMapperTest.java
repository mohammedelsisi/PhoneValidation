package com.jumia.phoneverification.backend.service.mapper;

import com.jumia.phoneverification.backend.mapper.CustomerMapper;
import com.jumia.phoneverification.backend.model.dto.CustomerDto;
import com.jumia.phoneverification.backend.model.dto.CustomerDtoPage;
import com.jumia.phoneverification.backend.model.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class CustomerMapperTest {

    private static final String CUSTOMER_NAME = "Customer Name";
    private static final String CUSTOMER_PHONE = "(212) 698054317";
    private static final String CUSTOMER_COUNTRY = "Morocco";
    private static final Integer CUSTOMER_ID = 1;
    private static final int PAGE_NUMBER = 0;
    @Autowired
    CustomerMapper customerMapper;
    @Mock
    Page<Customer> customerPage;
    List<Customer> customerList;

    @BeforeEach
    void arrange() {
        Customer customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_PHONE);
        customerList = new ArrayList<>();
        customerList.add(customer);
    }

    @Test
    @DisplayName("Test mapping Customer List to customer Dto list")
    void testGetCustomerDtoPage() {

        //Act
        CustomerDtoPage customerDtoPage = customerMapper.getCustomerDtoPage(customerList, PAGE_NUMBER);
        CustomerDto actual = customerDtoPage.getCustomerDtoList().get(0);

        //Assert
        assertEquals(1L, customerDtoPage.getNumberOfPages());
        assertEquals(CUSTOMER_NAME, actual.getName());
        assertEquals(CUSTOMER_PHONE, actual.getPhone());
        assertEquals(CUSTOMER_COUNTRY, actual.getCountryName());
    }

    @Test
    @DisplayName("Test mapping Customer Page to customer Dto list")
    void testMapEntityPageToDto() {

        //Arrange
        given(customerPage.getTotalPages()).willReturn(1);
        given(customerPage.toList()).willReturn(customerList);

        //Act
        CustomerDtoPage customerDtoPage = customerMapper.mapEntityPageToDto(customerPage);
        CustomerDto actual = customerDtoPage.getCustomerDtoList().get(0);

        //Assert
        assertEquals(1L, customerDtoPage.getNumberOfPages());
        assertEquals(CUSTOMER_NAME, actual.getName());
        assertEquals(CUSTOMER_PHONE, actual.getPhone());
        assertEquals(CUSTOMER_COUNTRY, actual.getCountryName());
    }

    @Test
    @DisplayName("Test mapping Customer list to countries set")
    void testGetCountriesFromCustomers() {

        //Act
        Set<String> countriesFromCustomers = customerMapper.getCountriesFromCustomers(customerList);
        //Assert
        assertThat(countriesFromCustomers).isNotEmpty().contains(CUSTOMER_COUNTRY);
    }

}
