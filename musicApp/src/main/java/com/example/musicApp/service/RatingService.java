package com.example.musicApp.service;

import com.example.musicApp.dto.request.RatingRequestDto;
import com.example.musicApp.dto.response.RatingPageResponseDto;
import com.example.musicApp.dto.response.RatingResponseDto;

public interface RatingService {
    void rate(RatingRequestDto ratingRequestDto);

    void deleteById(Integer id);

    RatingPageResponseDto getAllRatings(Integer page, Integer count);

    RatingResponseDto getRatingByID(Integer id);
}
