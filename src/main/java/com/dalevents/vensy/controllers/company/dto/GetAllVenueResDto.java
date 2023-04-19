package com.dalevents.vensy.controllers.company.dto;

import java.util.List;

public record GetAllVenueResDto(Long id, String name, String address, String phoneNumber, int capacity,
        String description, List<String> services) {

}
