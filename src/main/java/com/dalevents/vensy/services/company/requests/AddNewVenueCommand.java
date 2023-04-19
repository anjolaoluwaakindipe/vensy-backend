package com.dalevents.vensy.services.company.requests;

public record AddNewVenueCommand(String name, String address, String phoneNumber, int capacity,
                String description) {

}
