package com.dalevents.vensy.integration.services;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
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
import com.dalevents.vensy.services.company.requests.AddNewVenueCommand;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;
import com.dalevents.vensy.services.company.response.CreateCompanyResponse;
import com.dalevents.vensy.services.company.response.GetAllVenuesReponse;

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

    @Test
    public void whenGivenACreateCompanyCommandWithAnExistingEmail_thenThrowAppError() {
        CreateCompanyCommand command = new CreateCompanyCommand("test", "1234567890", "dalevents@gmail.com",
                "123 test st");
        assertThrows(AppError.class, () -> {
            companyService.createCompany(command);
        });
    }

    @Test
    public void whenGivenAnAddVenueCommand_thenAddVenueToCompany() {
        Long companyId = 1L;

        AddNewVenueCommand command = new AddNewVenueCommand("test event hall", "22 test street", "12341234", 200,
                "Near the beach");

        companyService.addNewVenue(companyId, command);

        var company = companyRepository.findById(companyId);

        assertTrue(company.isPresent());
        assertEquals(1, company.get().getVenue().size());

    }

    @Test
    public void whenGivenAnAddVenueCommandWithInvalidCompanyId_thenThrowAppError() {
        Long companyId = 45L;
        AddNewVenueCommand command = new AddNewVenueCommand("test event hall", "22 test street", "12341234", 200,
                "Near the beach");

        assertThrows(AppError.class, () -> {
            companyService.addNewVenue(companyId, command);
        });
    }

    @Test
    public void whenGivenACompanyId_thenReturnAllVenuesOfCompany() {
        Long companyId = 1L;
        AddNewVenueCommand command = new AddNewVenueCommand("test event hall", "22 test street", "12341234", 200,
                "Near the beach");
        companyService.addNewVenue(companyId, command);
        List<GetAllVenuesReponse> response = companyService.getAllVenues(companyId);

        assertEquals(1, response.size());
        assertEquals(command.name(), response.get(0).name());
        assertEquals(command.address(), response.get(0).address());
        assertEquals(command.capacity(), response.get(0).capacity());
        assertEquals(command.phoneNumber(), response.get(0).phoneNumber());
        assertEquals(command.description(), response.get(0).description());
    }

    @Test
    public void whenGivenAnInvalidCompanyId_thenReturnAllVenuesOfCompany() {
        Long companyId = 1L;
        AddNewVenueCommand command = new AddNewVenueCommand("test event hall", "22 test street", "12341234", 200,
                "Near the beach");
        companyService.addNewVenue(companyId, command);
        assertThrows(AppError.class, ()->{
            companyService.getAllVenues(45L);
        });
    }
}
