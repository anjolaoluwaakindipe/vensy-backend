package com.dalevents.vensy.integration.services;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.dalevents.vensy.common.AppError;
import com.dalevents.vensy.models.Company;
import com.dalevents.vensy.repositories.CompanyRepository;
import com.dalevents.vensy.services.company.CompanyService;
import com.dalevents.vensy.services.company.CompanyServiceImpl;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;
import com.dalevents.vensy.services.company.response.CreateCompanyResponse;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(Lifecycle.PER_CLASS)
public class CompanyServicesImplTest {
    @Autowired
    private CompanyRepository companyRepository;

    private CompanyService companyService;

    private Company testCompany;

    @Before
    public void setup() {
        companyService = new CompanyServiceImpl(companyRepository);
        testCompany = Company.builder().address("Hello").name("DalEvents").email("dalevents@gmail.com")
                .phoneNumber("1234567890").build();
        companyRepository.save(testCompany);
    }

    @Test
    public void whenGivenACompanyId_thenReturnCompany() {
        var foundCompany = companyService.getCompanyById(testCompany.getId());

        assertEquals(foundCompany.id(), testCompany.getId());
        assertEquals(foundCompany.address(), testCompany.getAddress());
        assertEquals(foundCompany.email(), testCompany.getEmail());
    }

    @Test
    public void whenGiveACompanyIdThatDoesntExist_thenThrowAnAppError() {
        var exception = assertThrows(AppError.class, () -> {
            companyService.getCompanyById(3L);
        });

        assertEquals(exception.getStatusCode(), HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void whenGivenACreateCompanyCommand_thenCreateCompanyAndReturnTheCompanyAsAResponse() {
        CreateCompanyCommand command = new CreateCompanyCommand("test", "1234567890", "test4@gmail.com", "123 test st");
        CreateCompanyResponse response = companyService.createCompany(command);
        assertEquals(command.address(), response.address());
        assertEquals(command.email(), response.email());
        assertEquals(command.phoneNumber(), response.phoneNumber());
        assertEquals(command.name(), response.name());
    }
}
