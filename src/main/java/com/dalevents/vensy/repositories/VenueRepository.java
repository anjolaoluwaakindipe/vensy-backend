package com.dalevents.vensy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dalevents.vensy.models.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    Optional<Venue> findByVenueKey(String venueKey);
}
