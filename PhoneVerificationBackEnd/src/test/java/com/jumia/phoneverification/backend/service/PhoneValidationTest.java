package com.jumia.phoneverification.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PhoneValidationTest {

    @Autowired
    PhoneValidation phoneValidationService;
    @MockBean
    CountryCodeService countryCodeService;

    private static final String VALID_PHONE = "(212) 654642448";
    private static final String INVALID_PHONE = "(212) 6617344445";
    private static final String Morocco_REGEX = "\\(212\\)\\ ?[5-9]\\d{8}$";

    @BeforeEach
    public void arrange() {
        given(countryCodeService.getRegexByPhone(VALID_PHONE)).willReturn(Morocco_REGEX);
        given(countryCodeService.getRegexByPhone(INVALID_PHONE)).willReturn(Morocco_REGEX);
    }

    @Test
    @DisplayName("Test if the phone is valid")
    void testValidPhone() {
        boolean actual = phoneValidationService.isValidPhone(VALID_PHONE);
        assertTrue(actual,"Valid number was returned as invalid");
    }

    @Test
    @DisplayName("Test if the phone is invalid")
    void testInValidPhone() {
        boolean actual = phoneValidationService.isValidPhone(INVALID_PHONE);
        assertFalse(actual,"invalid number was returned as valid");
    }

}
