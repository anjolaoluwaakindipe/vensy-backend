package com.dalevents.vensy.services.company;

import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;
import com.dalevents.vensy.services.company.response.CreateCompanyResponse;
import com.dalevents.vensy.services.company.response.GetCompanyResponse;

public interface CompanyService {
    GetCompanyResponse getCompanyById(Long id);

    CreateCompanyResponse createCompany(CreateCompanyCommand command);
}
