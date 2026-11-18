package com.martinezjohnny324.style_fusion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinezjohnny324.style_fusion.models.Membresia;

public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
                Optional<Membresia> findById(Long id);
}
