package com.dalevents.vensy.services.company;

import java.util.List;

import com.dalevents.vensy.services.company.requests.AddNewVenueCommand;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;
import com.dalevents.vensy.services.company.response.CreateCompanyResponse;
import com.dalevents.vensy.services.company.response.GetAllVenuesReponse;
import com.dalevents.vensy.services.company.response.GetCompanyResponse;

public interface CompanyService {
    GetCompanyResponse getCompanyById(Long id);

    public void addNewVenue(AddNewVenueCommand command);

    CreateCompanyResponse createCompany(CreateCompanyCommand command);

    public List<GetAllVenuesReponse> getAllVenues(Long companyId);
}
