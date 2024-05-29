package com.example.musicApp.dto.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayListResponseDto {
    private Integer ID;
    private Integer countOfMusic;
    private String nameOfPlaylist;
    private String nameOfMusic;
}







