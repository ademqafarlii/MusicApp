package com.example.musicApp.dto.request;

import jakarta.persistence.Id;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class MusicRequestDto {
    @NotNull(message = "ID of music cannot be null")
    @Positive(message = "ID cannot be negative")
    @Id
    private Integer id;
    @NotBlank(message = "Name of music cannot be empty")
    private String nameOfMusic;
    @NotBlank(message = "Name of singer cannot be empty")
    private String singer;

}
