package com.martinezjohnny324.style_fusion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinezjohnny324.style_fusion.models.Barbero;

public interface BarberoRepository extends JpaRepository <Barbero, Long> {
    Barbero findByNumero(Long id);
    
}
