package com.example.musicApp.service;

import com.example.musicApp.dto.request.MusicRequestDto;
import com.example.musicApp.dto.request.PlayListRequestDto;
import com.example.musicApp.dto.response.MusicResponseDto;
import com.example.musicApp.dto.response.PlayListResponseDto;

import java.util.List;

public interface PlayListService {
    void createNewPlaylist(PlayListRequestDto playListRequestDto);
    void deletePlayListByID(Integer id);
    void deletePlayListByName(String nameOfPlayList);
    void addMusicToPlaylist(PlayListRequestDto playListRequestDto);
    List<PlayListResponseDto> getAllPlayListOrder();
    List<MusicResponseDto> getAllMusicsByPlaylistName(String nameOfPlaylist);
    void deleteMusicFromPlaylist(PlayListRequestDto playListRequestDto);
}
