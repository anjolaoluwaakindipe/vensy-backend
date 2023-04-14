package com.dalevents.vensy.services.company.requests;

public record CreateCompanyCommand(String name , String phoneNumber, String email, String address) {
    
}
