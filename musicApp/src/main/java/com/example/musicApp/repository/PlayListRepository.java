package com.example.musicApp.repository;

import com.example.musicApp.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface PlayListRepository extends JpaRepository<PlayList,Integer> {

    Optional<PlayList> getByNameOfPlaylist(String nameOfPlaylist);
    void deleteByNameOfPlaylist(String nameOfPlaylist);

}
