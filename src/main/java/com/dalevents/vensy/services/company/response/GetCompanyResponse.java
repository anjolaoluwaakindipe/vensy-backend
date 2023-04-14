package com.dalevents.vensy.services.company.response;

import java.util.Set;

import com.dalevents.vensy.models.Venue;

public record GetCompanyResponse(Long id, String name, String address, String phoneNumber, String email, Set<Venue> venue) {
    
}
