package com.example.musicApp.mapper;


import com.example.musicApp.dto.request.RatingRequestDto;
import com.example.musicApp.dto.response.RatingResponseDto;
import com.example.musicApp.exception.RatingNotFoundException;
import com.example.musicApp.model.Rating;
import org.springframework.stereotype.Service;

@Service
public class RatingMapper {

    public Rating toRatingModel(RatingRequestDto ratingRequestDto){
        if (ratingRequestDto==null){
            throw new RatingNotFoundException("Rating cannot be null");
        }

        return Rating.builder()
                .rate(ratingRequestDto.getRate())
                .ID(ratingRequestDto.getID())
                .createdAt(ratingRequestDto.getCreatedAt())
                .music(ratingRequestDto.getMusic())
                .build();
    }

    public RatingResponseDto toRatingDto(Rating rating){
        if (rating==null){
            throw new RatingNotFoundException("Rating not found");
        }

        return RatingResponseDto.builder()
                .rate(rating.getRate())
                .ID(rating.getID())
                .createdAt(rating.getCreatedAt())
                .music(rating.getMusic())
                .build();
    }
}
