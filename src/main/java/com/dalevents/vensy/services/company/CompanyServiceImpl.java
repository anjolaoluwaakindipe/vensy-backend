package com.dalevents.vensy.services.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dalevents.vensy.common.AppError;
import com.dalevents.vensy.models.Company;
import com.dalevents.vensy.models.Specialty;
import com.dalevents.vensy.models.Venue;
import com.dalevents.vensy.repositories.CompanyRepository;
import com.dalevents.vensy.services.company.requests.AddNewVenueCommand;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;
import com.dalevents.vensy.services.company.response.CreateCompanyResponse;
import com.dalevents.vensy.services.company.response.GetAllVenuesReponse;
import com.dalevents.vensy.services.company.response.GetCompanyResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
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
                .phoneNumber(command.phoneNumber()).build();

        newCompany = companyRepository.save(newCompany);

        return new CreateCompanyResponse(newCompany.getId(), newCompany.getName(), newCompany.getPhoneNumber(),
                newCompany.getEmail(), newCompany.getAddress());
    }

    @Override
    public void addNewVenue(Long companyId, AddNewVenueCommand command) {
        if (!companyRepository.existsById(companyId)) {
            throw new AppError(HttpStatus.NOT_FOUND.value(), "Company ID does not exists");
        }

        Company existingComapany = companyRepository.findById(companyId).get();

        var newVenue = Venue.builder().address(command.address()).capacity(command.capacity())
                .description(command.description()).name(command.name()).phoneNumber(command.phoneNumber()).build();

        existingComapany.getVenue().add(newVenue);

        companyRepository.save(existingComapany);
    }

    @Override
    public List<GetAllVenuesReponse> getAllVenues(Long companyId) {
        var companyOpt = companyRepository.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new AppError(HttpStatus.NOT_FOUND.value(), "Company not found");
        }

        return companyOpt.get().getVenue().stream().map(venue -> {
            var specialtyList = new ArrayList<String>();
            for (Specialty specialty : venue.getServices()) {
                specialtyList.add(specialty.getName());
            }

            return new GetAllVenuesReponse(venue.getId(), venue.getName(), venue.getAddress(), venue.getPhoneNumber(),
                    venue.getCapacity(), venue.getDescription(), specialtyList);
        }).toList();
    }
}
