package com.dalevents.vensy.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Getter
@Setter
// @EqualsAndHashCode(exclude = "venues")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String address;
    String phoneNumber;
    @Column(unique = true)
    String email;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "company")
    @Builder.Default
    Set<Venue> venues = new HashSet<>();

    @jakarta.persistence.OneToOne(cascade = CascadeType.ALL)
    AppUser user;

    public void addVenue(Venue venue) {
        venues.add(venue);
        venue.setCompany(this);
    }

    public void removeVenue(Venue venue) {
        venues.remove(venue);
        venue.setCompany(null);
    }

}
