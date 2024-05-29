package com.example.musicApp.dto.request;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConnectionRequestDto {
    @Id
    @NotNull(message = "Id cannot be null")
    @Positive(message = "ID cannot be negative")
    Integer id;
    @NotBlank(message = "User name cannot be blank")
    String userName;
    @NotNull(message = "Count of follower cannot be null")
    private Integer countOfFollower;
    @NotNull(message = "Count of follow cannot be null")
    private Integer countOfFollow;
}
