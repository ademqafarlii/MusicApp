package com.example.musicApp.controller;
import com.example.musicApp.dto.request.RatingRequestDto;
import com.example.musicApp.dto.response.RatingPageResponseDto;
import com.example.musicApp.dto.response.RatingResponseDto;
import com.example.musicApp.service.impl.RatingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/a1/ratings")
@RestController
public class RatingController {
    private final RatingServiceImpl ratingService;

    public RatingController(RatingServiceImpl ratingService) {
        this.ratingService = ratingService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/rate")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void rate(RatingRequestDto ratingRequestDto) {
        ratingService.rate(ratingRequestDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete-by-id/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deleteById(@PathVariable Integer id) {
       ratingService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-all-ratings")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public RatingPageResponseDto getAllRatings
            (@RequestParam(name = "page")Integer page,@RequestParam(name = "count") Integer count) {
        return ratingService.getAllRatings(page,count);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-rating-by-id/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public RatingResponseDto getRatingByID(@PathVariable Integer id) {
        return ratingService.getRatingByID(id);
    }
}
