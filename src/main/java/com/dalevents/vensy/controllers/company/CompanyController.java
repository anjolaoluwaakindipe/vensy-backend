package com.dalevents.vensy.controllers.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalevents.vensy.controllers.company.dto.AddNewVenueReqDto;
import com.dalevents.vensy.controllers.company.dto.CreateCompanyReqDto;
import com.dalevents.vensy.controllers.company.dto.CreateCompanyResDto;
import com.dalevents.vensy.controllers.company.dto.GetAllVenueResDto;
import com.dalevents.vensy.controllers.company.dto.GetCompanyPublicInfoDto;
import com.dalevents.vensy.mappings.CompanyMapping;
import com.dalevents.vensy.services.company.CompanyService;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapping companyMapping;

    @PostMapping
    ResponseEntity<CreateCompanyResDto> createACompany(@Validated @RequestBody CreateCompanyReqDto body) {
        CreateCompanyCommand params = companyMapping.createCompanyDtoToCommand(body);
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

    @PostMapping("/{companyId}/venue")
    ResponseEntity<?> addNewVenue(@PathVariable("companyId") Long companyId, @Validated AddNewVenueReqDto request) {
        companyService.addNewVenue(companyId, companyMapping.addNewVenueDtoToCommand(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{companyId}/venue")
    ResponseEntity<List<GetAllVenueResDto>> getAllVenues(@PathVariable("companyId") Long companyId) {
        var serviceResponse = companyService.getAllVenues(companyId);
        var response = companyMapping.getAllVenueResponseToDto(serviceResponse);
        return new ResponseEntity<List<GetAllVenueResDto>>(response, HttpStatus.OK);
    }
}
