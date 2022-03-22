package com.jumia.phoneverification.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CountryCodeServiceTest {
    private static final String Morocco_Phone = "(212) 654642448";
    private static final String INVALID_Phone = "invalid phone";
    private static final String Morocco_REGEX = "\\(212\\)\\ ?[5-9]\\d{8}$";
    private static final String Morocco_COUNTRY_NAME = "Morocco";

    @Autowired
    CountryCodeService countryCodeService;

    @Test
    @DisplayName("Test returned Regex By phone")
    void testGetRegexByPhone() {
        String actualRegex = countryCodeService.getRegexByPhone(Morocco_Phone);
        assertEquals(Morocco_REGEX, actualRegex, "wrong regex was returned");
        assertEquals(Morocco_REGEX, actualRegex);
    }

    @Test
    @DisplayName("Test returned country name By phone")
    void testGetCountryName() {
        String actualCountry = countryCodeService.getCountryNameByPhone(Morocco_Phone);
        assertEquals(Morocco_COUNTRY_NAME, actualCountry, "wrong country name was returned");
    }

    @Test
    @DisplayName("Test invalid country code")
    void testInvalidCountryCode() {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            countryCodeService.getCountryNameByPhone(INVALID_Phone);
        });

        assertEquals("No country code was found",runtimeException.getMessage());
    }
}
