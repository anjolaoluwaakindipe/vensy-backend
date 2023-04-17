package com.dalevents.vensy.controllers.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dalevents.vensy.controllers.company.dto.AddNewVenueReqDto;
import com.dalevents.vensy.controllers.company.dto.CreateCompanyReqDto;
import com.dalevents.vensy.controllers.company.dto.CreateCompanyResDto;
import com.dalevents.vensy.controllers.company.dto.GetCompanyPublicInfoDto;
import com.dalevents.vensy.mappings.CompanyMapping;
import com.dalevents.vensy.services.company.CompanyService;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Controller()
@Slf4j
@RequestMapping("/api/v1/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyMapping companyMapping;

    @PostMapping
    ResponseEntity<CreateCompanyResDto> createACompany(@Validated @RequestBody CreateCompanyReqDto body) {
        CreateCompanyCommand params = companyMapping.creatCompanyDtoToCommand(body);
        var serviceResponse = companyService.createCompany(params);
        var response = companyMapping.createCommpanyResponseToDto(serviceResponse);
        return new ResponseEntity<CreateCompanyResDto>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{companyId}")
    ResponseEntity<GetCompanyPublicInfoDto> getCompanyPublicInfo(@PathVariable("companyId") Long id) {
        var serviceResponse = companyService.getCompanyById(id);
        var response = companyMapping.getCompanyPublicInfoResponseToDto(serviceResponse);
        return new ResponseEntity<GetCompanyPublicInfoDto>(response, HttpStatus.OK);
    }

    @PostMapping("/venue")
    ResponseEntity<?> addNewVenue(@Validated AddNewVenueReqDto request) {
        companyService.addNewVenue(companyMapping.addNewVenueDtoToCommand(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
