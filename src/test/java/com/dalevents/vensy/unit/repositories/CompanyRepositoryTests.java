package com.dalevents.vensy.unit.repositories;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dalevents.vensy.models.AppUser;
import com.dalevents.vensy.models.Company;
import com.dalevents.vensy.models.enums.Role;
import com.dalevents.vensy.repositories.AppUserRepository;
import com.dalevents.vensy.repositories.CompanyRepository;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CompanyRepositoryTests {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    private Company testCompany;
    private AppUser appUser1;

    @BeforeAll
    void setUp() {
        appUser1 = AppUser.builder().email("testuser@gmail.com").firstname("Test").lastname("Test").role(Role.COMPANY)
                .build();
        testCompany = Company.builder().address("29 Test St").email("test@gmail.com").name("Test Test")
                .user(appUser1)
                .phoneNumber("0902345").build();
        appUser1.setCompany(testCompany);
        appUserRepository.save(appUser1);
    }

    @AfterAll
    void cleanUp() {
        companyRepository.delete(testCompany);
    }

    @Test
    void whenGivenAValidEmail_thenReturnTrue() {
        boolean result = companyRepository.existsByEmail(testCompany.getEmail());
        assertTrue(result);
    }

}
