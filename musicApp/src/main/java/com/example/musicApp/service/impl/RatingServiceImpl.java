package com.example.musicApp.service.impl;
import com.example.musicApp.dto.request.RatingRequestDto;
import com.example.musicApp.dto.response.RatingPageResponseDto;
import com.example.musicApp.dto.response.RatingResponseDto;
import com.example.musicApp.exception.RatingNotFoundException;
import com.example.musicApp.mapper.RatingMapper;
import com.example.musicApp.model.Rating;
import com.example.musicApp.repository.RatingRepository;
import com.example.musicApp.service.RatingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public void rate(RatingRequestDto ratingRequestDto) {
        ratingRepository.save(ratingMapper.toRatingModel(ratingRequestDto));
    }

    @Override
    public void deleteById(Integer id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public RatingPageResponseDto getAllRatings(Integer page, Integer count) {

        Page<Rating> ratingPage = ratingRepository.findAll(PageRequest.of(page, count));
        if (ratingPage.isEmpty()) {
            throw new RatingNotFoundException("Rating not found");
        }
        return new RatingPageResponseDto(
                ratingPage.getContent().stream().map(ratingMapper::toRatingDto).toList()
                , ratingPage.getTotalElements()
                , ratingPage.getTotalPages()
                , ratingPage.hasNext()
        );

    }

    @Override
    public RatingResponseDto getRatingByID(Integer id) {
        List<Rating> ratings = ratingRepository.findAll();
        if (ratings.isEmpty()) {
            throw new RatingNotFoundException("Rating not found");
        }
        return ratingRepository.findById(id)
                .stream()
                .map(ratingMapper::toRatingDto)
                .findFirst()
                .orElseThrow(() -> new RatingNotFoundException("Rating not found"));
    }
}
