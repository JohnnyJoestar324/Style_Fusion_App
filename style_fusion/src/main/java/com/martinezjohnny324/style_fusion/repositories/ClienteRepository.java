package com.martinezjohnny324.style_fusion.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.martinezjohnny324.style_fusion.models.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
        Optional<Cliente> findById(Long id);
        Cliente findByEmail(String email);


}
