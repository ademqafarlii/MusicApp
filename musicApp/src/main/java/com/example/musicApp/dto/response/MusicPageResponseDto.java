package com.example.musicApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicPageResponseDto {
    List<MusicResponseDto> musicList;
    long totalElements;
    int totalPages;
    boolean hasNextPage;
}
