package com.martinezjohnny324.style_fusion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.martinezjohnny324.style_fusion.models.ClienteBarbero;

public interface ClienteBarberoRepository extends JpaRepository <ClienteBarbero, Long> {

    ClienteBarbero findById(long id);
        

}
