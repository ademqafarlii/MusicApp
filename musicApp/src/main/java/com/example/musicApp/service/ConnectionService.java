package com.example.musicApp.service;

import com.example.musicApp.dto.request.ConnectionRequestDto;
import com.example.musicApp.dto.response.ConnectionResponseDto;
import java.util.List;

public interface ConnectionService {
    void follow(ConnectionRequestDto dto);

    void unFollow(ConnectionRequestDto dto);

    List<ConnectionResponseDto> seeFollowerList();

    ConnectionResponseDto getFollowerById(Integer ID);

    ConnectionResponseDto getFollowerByName(String name);

    List<ConnectionResponseDto> getFollowList();

    ConnectionResponseDto getFollowById(Integer ID);

    ConnectionResponseDto getFollowByName(String name);

}
