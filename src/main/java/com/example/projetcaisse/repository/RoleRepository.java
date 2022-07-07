package com.example.projetcaisse.repository;

import java.util.Optional;
import com.example.projetcaisse.model.entity.ERole;
import com.example.projetcaisse.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}