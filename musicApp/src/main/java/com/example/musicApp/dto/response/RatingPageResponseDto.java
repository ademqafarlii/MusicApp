package com.example.musicApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RatingPageResponseDto {

    List<RatingResponseDto> userList;
    long totalElements;
    int totalPages;
    boolean hasNextPage;
}
