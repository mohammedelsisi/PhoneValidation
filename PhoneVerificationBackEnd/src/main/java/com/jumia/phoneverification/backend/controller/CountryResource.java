package com.jumia.phoneverification.backend.controller;

import com.jumia.phoneverification.backend.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping(value = "/countries")
public class CountryResource {

    private final CountryService countryService;

    @GetMapping
    public Set<String> getAvailableCountries() {
        return countryService.getCountries();
    }
}
