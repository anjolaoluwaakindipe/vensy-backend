package com.dalevents.vensy.services.auth.request;

public record RegisterCompanyUserCommand(String firstname, String lastname, String email, String password) {

}
