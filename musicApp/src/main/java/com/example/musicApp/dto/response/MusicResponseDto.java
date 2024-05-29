package com.example.musicApp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicResponseDto {
    private int countOfMusic;
    private String nameOfMusic;
    private String singer;
    private Integer id;


}
