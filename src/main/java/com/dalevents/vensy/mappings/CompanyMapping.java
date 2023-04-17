package com.dalevents.vensy.mappings;

import org.mapstruct.Mapper;

import com.dalevents.vensy.controllers.company.dto.AddNewVenueReqDto;
import com.dalevents.vensy.controllers.company.dto.CreateCompanyReqDto;
import com.dalevents.vensy.controllers.company.dto.CreateCompanyResDto;
import com.dalevents.vensy.controllers.company.dto.GetCompanyPublicInfoDto;
import com.dalevents.vensy.services.company.requests.AddNewVenueCommand;
import com.dalevents.vensy.services.company.requests.CreateCompanyCommand;
import com.dalevents.vensy.services.company.response.CreateCompanyResponse;
import com.dalevents.vensy.services.company.response.GetCompanyResponse;

@Mapper(componentModel = "spring")
public interface CompanyMapping {
    CreateCompanyCommand creatCompanyDtoToCommand(CreateCompanyReqDto dto);
    CreateCompanyResDto createCommpanyResponseToDto(CreateCompanyResponse response);
    GetCompanyPublicInfoDto getCompanyPublicInfoResponseToDto(GetCompanyResponse response);
    AddNewVenueCommand addNewVenueDtoToCommand(AddNewVenueReqDto dto );
}
