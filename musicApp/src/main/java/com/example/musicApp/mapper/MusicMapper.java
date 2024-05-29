package com.example.musicApp.mapper;

import com.example.musicApp.dto.request.MusicRequestDto;
import com.example.musicApp.dto.response.MusicResponseDto;
import com.example.musicApp.exception.MusicNotFoundException;
import com.example.musicApp.model.Music;
import org.springframework.stereotype.Service;

@Service
public class MusicMapper {
    public Music toMusicModel(MusicRequestDto musicRequestDto) {
        if (musicRequestDto == null) {
            throw new MusicNotFoundException("Music not found");
        }
        return Music.builder()
                .id(musicRequestDto.getId())
                .name(musicRequestDto.getNameOfMusic())
                .singer(musicRequestDto.getSinger())
                .build();
    }

    public MusicResponseDto toMusicResponseDto(Music music) {
        if (music == null) {
            throw new MusicNotFoundException("Music not found");
        }
        MusicResponseDto musicResponseDto = new MusicResponseDto();
        musicResponseDto.setNameOfMusic(music.getName());
        musicResponseDto.setSinger(music.getSinger());
        musicResponseDto.setId(music.getId());
        return musicResponseDto;
    }
}
