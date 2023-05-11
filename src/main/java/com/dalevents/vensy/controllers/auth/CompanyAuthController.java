package com.dalevents.vensy.controllers.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalevents.vensy.controllers.auth.dto.RegisterCompanyUserReqDto;
import com.dalevents.vensy.mappings.AuthMapping;
import com.dalevents.vensy.services.auth.AuthService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth/company")
@AllArgsConstructor
public class CompanyAuthController {

    private final AuthService authService;
    private final AuthMapping authMapping;


    @PostMapping("/register")
    public ResponseEntity<?> registerCompanyUser(RegisterCompanyUserReqDto req){
        var command = authMapping.registerCompanyUserDtoToCommand(req);
        authService.registerCompanyUser(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
