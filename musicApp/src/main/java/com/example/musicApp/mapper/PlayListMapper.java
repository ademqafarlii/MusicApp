package com.example.musicApp.mapper;

import com.example.musicApp.dto.request.PlayListRequestDto;
import com.example.musicApp.dto.response.PlayListResponseDto;
import com.example.musicApp.exception.PlayListNotFoundException;
import com.example.musicApp.model.Music;
import com.example.musicApp.model.PlayList;
import org.springframework.stereotype.Service;

@Service
public class PlayListMapper {

    public PlayList toPlaylistModel(PlayListRequestDto playlistResponseDto) {
        if (playlistResponseDto == null) {
            throw new PlayListNotFoundException("Playlist not found");
        }
        return PlayList.builder()
                .ID(playlistResponseDto.getID())
                .nameOfPlaylist(playlistResponseDto.getNameOfPlaylist())
                .countOfMusic(playlistResponseDto.getCountOfMusic())
                .nameOfMusic(playlistResponseDto.getNameOfMusic())
                .build();
    }

    public PlayListResponseDto playListResponseDto(PlayList playlist) {
        if (playlist == null) {
            throw new PlayListNotFoundException("Playlist not found");

        }
        return PlayListResponseDto.builder()
                .ID(playlist.getID())
                .countOfMusic(playlist.getCountOfMusic())
                .nameOfMusic(playlist.getNameOfMusic())
                .nameOfPlaylist(playlist.getNameOfPlaylist())
                .build();
    }

}
