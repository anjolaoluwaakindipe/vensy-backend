package com.dalevents.vensy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dalevents.vensy.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    public boolean existsByEmail(String email);
    public Optional<Company> findByKey(String key);
}
