package com.dalevents.vensy.controllers.company.dto;

import java.util.Set;

import com.dalevents.vensy.models.Venue;

public record GetCompanyPublicInfoDto(Long id, String name,String  address, String phoneNumber, String email, Set<Venue> venue) {
    
}
