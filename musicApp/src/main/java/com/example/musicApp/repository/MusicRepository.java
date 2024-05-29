package com.example.musicApp.repository;

import com.example.musicApp.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {
    List<Music> findByName(String tittle);
    List<Music> findBySinger(String name);

}
