package com.dalevents.vensy.services.company.response;

import java.util.List;

public record  GetAllVenuesReponse(Long id, String name, String address, String phoneNumber, int capacity, String description, List<String> services) {

}
