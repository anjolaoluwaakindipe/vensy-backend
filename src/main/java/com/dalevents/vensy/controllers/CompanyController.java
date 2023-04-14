package com.dalevents.vensy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.dalevents.vensy.services.company.CompanyService;

@Controller
public class CompanyController {
    @Autowired
    CompanyService companyService;


}
