package com.dalevents.vensy.controllers.company.dto;

public record AddNewVenueReqDto(Long companyId, String name, String address, String phoneNumber, int capacity,
        String description) {

}
