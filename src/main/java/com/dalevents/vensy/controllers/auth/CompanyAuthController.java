package com.dalevents.vensy.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalevents.vensy.controllers.auth.dto.CreateCompanyReqDto;
import com.dalevents.vensy.controllers.auth.dto.CreateCompanyResDto;
import com.dalevents.vensy.mappings.CompanyMapping;
import com.dalevents.vensy.services.company.CompanyService;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/company")
@AllArgsConstructor
public class CompanyAuthController {
    private CompanyService companyService;

    private CompanyMapping companyMapping;

    @PostMapping("/register")
    ResponseEntity<CreateCompanyResDto> createACompany(@Validated @RequestBody CreateCompanyReqDto body) {
        CreateCompanyCommand params = companyMapping.createCompanyDtoToCommand(body);
        var serviceResponse = companyService.createCompany(params);
        var response = companyMapping.createCommpanyResponseToDto(serviceResponse);
        return new ResponseEntity<CreateCompanyResDto>(response, HttpStatus.CREATED);
    }
}
