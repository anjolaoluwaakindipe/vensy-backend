package com.dalevents.vensy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dalevents.vensy.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
