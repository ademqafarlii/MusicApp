package com.example.musicApp.repository;

import com.example.musicApp.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
    Optional<Connection> getByUserName(String userName);

}
