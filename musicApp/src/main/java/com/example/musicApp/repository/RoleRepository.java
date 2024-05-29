package com.example.musicApp.repository;

import com.example.musicApp.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel,Integer> {
    Optional<RoleModel> findByName(String name);

}
