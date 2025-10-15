package com.martinezjohnny324.style_fusion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.martinezjohnny324.style_fusion.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
        Optional<Users> findByUsername(String username);

}
