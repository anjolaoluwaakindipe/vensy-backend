package com.dalevents.vensy.controllers.company.dto;

public record AddNewVenueReqDto(String name, String address, String phoneNumber, int capacity,
        String description) {

}
