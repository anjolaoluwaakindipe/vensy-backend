package com.dalevents.vensy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dalevents.vensy.entities.Venue;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    
}
