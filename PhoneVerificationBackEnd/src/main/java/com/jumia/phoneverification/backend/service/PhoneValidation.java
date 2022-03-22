package com.jumia.phoneverification.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PhoneValidation {
    private final CountryCodeService countryCodeService;

    public boolean isValidPhone(String phone) {
        String regexByCountryCode = countryCodeService.getRegexByPhone(phone);
        Pattern pattern = Pattern.compile(regexByCountryCode);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

}
