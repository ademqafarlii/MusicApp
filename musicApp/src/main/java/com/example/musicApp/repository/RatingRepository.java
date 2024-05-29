package com.example.musicApp.repository;

import com.example.musicApp.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
}
