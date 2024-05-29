package com.example.musicApp.dto.request;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PlayListRequestDto {

    @Id
    @NotNull(message = "ID cannot be null")
    private Integer ID;
    @NotNull(message = "Count of music cannot be null")
    private Integer countOfMusic;
    @NotBlank(message = "Name of Playlist cannot be blank")
    private String nameOfPlaylist;
    @NotBlank(message = "Name of music cannot be blank")
    private String nameOfMusic;
    @NotBlank(message = "musicTittle cannot be blank")
    private String musicTitle;
    @NotBlank(message = "musicArtist cannot be blank")
    private String musicArtist;
    @NotNull(message = "musicID cannot be null")
    private Integer musicId;
}