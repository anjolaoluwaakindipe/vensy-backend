package com.dalevents.vensy.mappings;

import org.mapstruct.Mapper;

import com.dalevents.vensy.controllers.auth.dto.RegisterCompanyUserReqDto;
import com.dalevents.vensy.services.auth.request.RegisterCompanyUserCommand;

@Mapper(componentModel = "spring")
public interface AuthMapping {
    RegisterCompanyUserCommand registerCompanyUserDtoToCommand(RegisterCompanyUserReqDto req);
}
