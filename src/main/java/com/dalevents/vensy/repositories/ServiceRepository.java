
package com.dalevents.vensy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dalevents.vensy.models.Specialty;

public interface ServiceRepository extends JpaRepository<Specialty, Long> {

}
