package com.example.musicApp.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "follower")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String userName;

    @Column(name = "count_of_follower")
    private Integer countOfFollower;

    @Column(name = "count_of_follow")
    private Integer countOfFollow;

    public Connection(Integer id, String userName, Integer countOfFollower, Integer countOfFollow) {
        this.id = id;
        this.userName = userName;
        this.countOfFollower = countOfFollower;
        this.countOfFollow = countOfFollow;
    }

    public Connection() {
    }
}
