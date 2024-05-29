package com.example.musicApp.service;

import com.example.musicApp.dto.request.MusicRequestDto;
import com.example.musicApp.dto.response.MusicPageResponseDto;
import com.example.musicApp.dto.response.MusicResponseDto;
import java.util.List;


public interface MusicService {
    MusicResponseDto getById(Integer id);

    MusicPageResponseDto getAll(int page, int count);

    void addNewMusicToLibrary(MusicRequestDto musicRequestDto);

    void deleteMusicFromLibrary(Integer id);

    MusicPageResponseDto getAllMusicsByAlphabetOrder(Integer page, Integer count);

    MusicPageResponseDto getAllMusicsByReversedAlphabetOrder(Integer page, Integer count);

    MusicResponseDto findByName(String name);

    List<MusicResponseDto> findBySingerName(String name);

}
