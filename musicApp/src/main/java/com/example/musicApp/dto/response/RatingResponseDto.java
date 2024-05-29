package com.example.musicApp.dto.response;
import com.example.musicApp.model.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingResponseDto {

    private Integer ID;
    private String message;
    private Integer rate;
    private LocalDateTime createdAt;
    private Music music;

}
