package com.dalevents.vensy.mappings;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

import com.dalevents.vensy.controllers.company.dto.*;
import com.dalevents.vensy.services.company.requests.*;
import com.dalevents.vensy.services.company.response.*;

@Mapper(componentModel = "spring")
public interface CompanyMapping {

    CreateCompanyCommand creatCompanyDtoToCommand(CreateCompanyReqDto dto);

    CreateCompanyResDto createCommpanyResponseToDto(CreateCompanyResponse response);

    GetCompanyPublicInfoDto getCompanyPublicInfoResponseToDto(GetCompanyResponse response);

    AddNewVenueCommand addNewVenueDtoToCommand(AddNewVenueReqDto dto);
}
