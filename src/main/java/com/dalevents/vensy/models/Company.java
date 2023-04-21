package com.dalevents.vensy.models;

import java.util.Set;
import java.util.UUID;

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
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String address;
    String phoneNumber;
    @Column(unique = true)
    String email;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Venue> venue;
    @Column(unique = true)
    @Builder.Default
    String key = UUID.randomUUID().toString();
}
