package com.jumia.phoneverification.backend.mapper;

import com.jumia.phoneverification.backend.model.dto.CustomerDto;
import com.jumia.phoneverification.backend.model.dto.CustomerDtoPage;
import com.jumia.phoneverification.backend.model.entity.Customer;
import com.jumia.phoneverification.backend.service.CountryCodeService;
import com.jumia.phoneverification.backend.service.PhoneValidation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private final ModelMapper modelMapper;
    private final PhoneValidation phoneValidation;
    private final CountryCodeService countryCodeService;

    public CustomerDtoPage mapEntityPageToDto(Page<Customer> all) {
        CustomerDtoPage customerDtoPage = new CustomerDtoPage();
        customerDtoPage.setNumberOfPages(all.getTotalPages());
        customerDtoPage.setCustomerDtoList(all.toList().stream().map(this::getCustomerDtoFromEntity).collect(toList()));
        return customerDtoPage;
    }

    public CustomerDtoPage getCustomerDtoPage(List<Customer> customerListBeforePagination, int pageNumber) {
        int numberOfPages = (int) Math.ceil((double) customerListBeforePagination.size() / DEFAULT_PAGE_SIZE);
        List<Customer> customerList = customerListBeforePagination.stream().skip((long) pageNumber * DEFAULT_PAGE_SIZE).limit(DEFAULT_PAGE_SIZE).collect(toList());
        CustomerDtoPage customerDtoPage = new CustomerDtoPage();
        customerDtoPage.setNumberOfPages(numberOfPages);
        customerDtoPage.setCustomerDtoList(customerList.stream().map(this::getCustomerDtoFromEntity).collect(toList()));
        return customerDtoPage;
    }

    public Set<String> getCountriesFromCustomers(List<Customer> customerList) {
        return customerList.stream()
                .map(Customer::getPhone)
                .map(countryCodeService::getCountryNameByPhone)
                .collect(Collectors.toSet());
    }

    private CustomerDto getCustomerDtoFromEntity(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setValid(phoneValidation.isValidPhone(customer.getPhone()));
        customerDto.setCountryName(countryCodeService.getCountryNameByPhone(customer.getPhone()));
        return customerDto;
    }


}
