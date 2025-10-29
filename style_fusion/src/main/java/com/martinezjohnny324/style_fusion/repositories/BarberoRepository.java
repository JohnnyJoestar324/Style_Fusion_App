package com.martinezjohnny324.style_fusion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinezjohnny324.style_fusion.models.Barbero;

public interface BarberoRepository extends JpaRepository <Barbero, Long> {
            Optional<Barbero> findById(Long id);

}
