package com.dalevents.vensy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dalevents.vensy.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    public boolean existsByEmail(String email);
}
