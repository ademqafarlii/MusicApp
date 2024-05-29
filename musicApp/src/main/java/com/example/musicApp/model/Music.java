package com.example.musicApp.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity(name = "music")
@Data
@Builder

public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "music_name")
    private String name;

    @Column(name = "singer")
    private String singer;

    @ManyToOne
    @JoinColumn(name = "name_of_playlist")
    private PlayList playList;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> rating;

    public Music(int id, String name, String singer, PlayList playList, List<Rating> rating) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.playList = playList;
        this.rating = rating;
    }

    public Music() {
    }
}
