package com.dalevents.vensy.services.auth;

import com.dalevents.vensy.services.auth.request.RegisterCompanyUserCommand;

public interface AuthService {
    void registerCompanyUser(RegisterCompanyUserCommand command);
}
