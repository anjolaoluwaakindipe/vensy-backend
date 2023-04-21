package com.dalevents.vensy.unit;



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

import com.dalevents.vensy.models.Company;
import com.dalevents.vensy.repositories.CompanyRepository;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CompanyRepositoryTests {
    @Autowired
    private CompanyRepository companyRepository;

    private Company testCompany;

    @BeforeAll
    void setUp(){
        testCompany = Company.builder().address("29 Test St").email("test@gmail.com").name("Test Test").phoneNumber("0902345").build();
        testCompany = companyRepository.save(testCompany);
    }

    @AfterAll
    void cleanUp(){
        companyRepository.delete(testCompany);
    }

    @Test
    void whenGivenAValidEmail_thenReturnTrue(){
        boolean result = companyRepository.existsByEmail(testCompany.getEmail());
        assertTrue(result);
    }

    @Test
    void whenGivenAValidKey_thenReturnCompany(){
        Optional<Company> company = companyRepository.findByKey(testCompany.getKey());

        assertTrue(company.isPresent());
        assertEquals(testCompany.getEmail(),company.get().getEmail());
        assertEquals(testCompany.getKey(), company.get().getKey());
        assertEquals(testCompany.getAddress(), company.get().getAddress());
        assertEquals(testCompany.getName(), company.get().getName());
        assertEquals(testCompany.getPhoneNumber(), company.get().getPhoneNumber());
    }

}
