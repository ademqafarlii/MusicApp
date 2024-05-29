package com.example.musicApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity(name = "rating")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Column(name = "message")
    private String message;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @JoinColumn(name = "music")
    @ManyToOne
    private Music music;


}
