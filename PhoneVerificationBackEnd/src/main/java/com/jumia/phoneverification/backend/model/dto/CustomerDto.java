package com.jumia.phoneverification.backend.model.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private String name;
    private String phone;
    private String countryName;
    private boolean isValid;
}
