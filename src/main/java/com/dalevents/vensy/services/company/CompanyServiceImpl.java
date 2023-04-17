package com.dalevents.vensy.services.company;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dalevents.vensy.common.AppError;
import com.dalevents.vensy.models.Company;
import com.dalevents.vensy.models.Venue;
import com.dalevents.vensy.repositories.CompanyRepository;
import com.dalevents.vensy.services.company.requests.AddNewVenueCommand;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;
import com.dalevents.vensy.services.company.response.CreateCompanyResponse;
import com.dalevents.vensy.services.company.response.GetCompanyResponse;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public GetCompanyResponse getCompanyById(Long id) {
        Optional<Company> companyOpt = companyRepository.findById(id);

        if (companyOpt.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND.value(), "Could not find Company");
        }

        Company company = companyOpt.get();

        return new GetCompanyResponse(company.getId(), company.getName(), company.getAddress(),
                company.getPhoneNumber(), company.getEmail(), company.getVenue());
    }

    @Override
    public CreateCompanyResponse createCompany(CreateCompanyCommand command) {

        if (companyRepository.existsByEmail(command.email())) {
            throw new AppError(HttpStatus.CONFLICT.value(), "Company with email Already exists!!!");
        }

        Company newCompany = Company.builder().email(command.email()).address(command.address()).name(command.name())
                .phoneNumber(command.name()).build();

        newCompany = companyRepository.save(newCompany);

        return new CreateCompanyResponse(newCompany.getId(), newCompany.getName(), newCompany.getPhoneNumber(),
                newCompany.getEmail(), newCompany.getAddress());
    }

    @Override
    public void addNewVenue(AddNewVenueCommand command) {
        if (!companyRepository.existsById(command.companyId())) {
            throw new AppError(HttpStatus.NOT_FOUND.value(), "Company ID does not exists");
        }

        Company existingComapany = companyRepository.findById(command.companyId()).get();

        var newVenue = Venue.builder().address(command.address()).capacity(command.capacity())
                .description(command.description()).name(command.name()).build();

        existingComapany.getVenue().add(newVenue);

        companyRepository.save(existingComapany);
    }
}
