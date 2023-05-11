package com.dalevents.vensy.mappings;

import java.util.List;

import org.mapstruct.Mapper;

import com.dalevents.vensy.controllers.auth.dto.CreateCompanyReqDto;
import com.dalevents.vensy.controllers.auth.dto.CreateCompanyResDto;
import com.dalevents.vensy.controllers.company.dto.*;
import com.dalevents.vensy.services.company.requests.*;
import com.dalevents.vensy.services.company.response.*;

@Mapper(componentModel = "spring")
public interface CompanyMapping {

    CreateCompanyCommand createCompanyDtoToCommand(CreateCompanyReqDto dto);

    CreateCompanyResDto createCommpanyResponseToDto(CreateCompanyResponse response);

    GetCompanyPublicInfoDto getCompanyPublicInfoResponseToDto(GetCompanyResponse response);

    AddNewVenueCommand addNewVenueDtoToCommand(AddNewVenueReqDto dto);

    List<GetAllVenueResDto> getAllVenueResponseToDto(List<GetAllVenuesReponse> response);
}
