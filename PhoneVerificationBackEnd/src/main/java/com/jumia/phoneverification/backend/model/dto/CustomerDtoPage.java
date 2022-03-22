package com.jumia.phoneverification.backend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDtoPage {
    private List<CustomerDto> customerDtoList;
    long numberOfPages;
}
