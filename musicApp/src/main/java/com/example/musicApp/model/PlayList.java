package com.example.musicApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity(name = "playlist")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(name = "count_of_music")
    private Integer countOfMusic;
    @Column(name = "name_of_playlist")
    private String nameOfPlaylist;
    @Column(name = "name_of_music")
    private String nameOfMusic;

    @OneToMany(mappedBy = "playList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Music> musicList;

}
