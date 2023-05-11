package com.dalevents.vensy.unit.repositories;

import static org.junit.Assert.assertEquals;

import java.text.CompactNumberFormat;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dalevents.vensy.models.Company;
import com.dalevents.vensy.models.Venue;
import com.dalevents.vensy.repositories.CompanyRepository;
import com.dalevents.vensy.repositories.VenueRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class VenueRepositoryUnitTests {

    @Autowired
    VenueRepository venueRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void whenGivenAValidKey_shouldReturnVenue() {
        Venue venue = Venue.builder().address("28 test street").phoneNumber("886900232").name("Test House")
                .capacity(230).build();
        Company company = Company.builder().address("29 test Street").email("test@email.com").phoneNumber("1238008232")
                .name("Tests and Sons").build();
        company.addVenue(venue);
        // venue.setCompany(company);

        company = companyRepository.save(company);


        var foundVenue = venueRepository.findByVenueKey(venue.getVenueKey());

        assertEquals(venue.getAddress(), foundVenue.get().getAddress());
        assertEquals(venue.getCapacity(), foundVenue.get().getCapacity());
        assertEquals(venue.getPhoneNumber(), foundVenue.get().getPhoneNumber());
        assertEquals(venue.getName(), foundVenue.get().getName());

        var foundCompany = foundVenue.get().getCompany();

        assertEquals(company.getId(), foundCompany.getId());
        assertEquals(venue.getAddress(), foundVenue.get().getAddress());
        assertEquals(1, foundCompany.getVenues().size());
    }
}
