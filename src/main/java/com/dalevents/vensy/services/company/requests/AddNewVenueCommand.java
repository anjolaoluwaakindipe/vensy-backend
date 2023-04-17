package com.dalevents.vensy.services.company.requests;

public record AddNewVenueCommand(Long companyId, String name, String address, String phoneNumber, int capacity,
                String description) {

}
