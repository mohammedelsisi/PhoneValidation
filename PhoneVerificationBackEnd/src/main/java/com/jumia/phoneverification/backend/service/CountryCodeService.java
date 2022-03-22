package com.jumia.phoneverification.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:countries-codes.properties")
@PropertySource("classpath:countries-regex.properties")
public class CountryCodeService {

    public static final String COUNTRY_CODE_REGEX = "\\d{3}";
    private final Environment env;

    public String getRegexByPhone(String phone) {
        String countryCodeByPhone = getCountryCodeByPhone(phone);
        String countryNameByCode = getCountryNameByCode(countryCodeByPhone);
        return env.getProperty(countryNameByCode + ".regexp");
    }

    public String getCountryNameByPhone(String phone) {
        return getCountryNameByCode(getCountryCodeByPhone(phone));
    }

    private String getCountryNameByCode(String countryCode) {
        return env.getProperty("country.code." + countryCode);
    }

    private String getCountryCodeByPhone(String phone) {
        Pattern pattern = Pattern.compile(COUNTRY_CODE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new RuntimeException("No country code was found");
    }
}
