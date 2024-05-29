package com.example.musicApp.dto.request;

import com.example.musicApp.model.Music;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingRequestDto {
    @NotNull(message = "id cannot be null")
    private Integer ID;

    private String message;
    @NotNull(message = "rate cannot be null")
    @Size(min = 0,max = 10,message = "min=0,max=10")
    private Integer rate;
    @NotBlank(message = "cratedAt cannot be blank")
    private LocalDateTime createdAt;

    private Music music;
}
