package com.example.musicApp.service.impl;
import com.example.musicApp.dto.request.PlayListRequestDto;
import com.example.musicApp.dto.response.MusicResponseDto;
import com.example.musicApp.dto.response.PlayListResponseDto;
import com.example.musicApp.exception.PlayListNotFoundException;
import com.example.musicApp.mapper.MusicMapper;
import com.example.musicApp.mapper.PlayListMapper;
import com.example.musicApp.model.Music;
import com.example.musicApp.model.PlayList;
import com.example.musicApp.repository.PlayListRepository;
import com.example.musicApp.service.PlayListService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayListServiceImpl implements PlayListService {
    private final PlayListRepository playListRepository;
    private final PlayListMapper playListMapper;
    private final MusicMapper musicMapper;



    public PlayListServiceImpl(PlayListRepository playListRepository
            , PlayListMapper playListMapper, MusicMapper musicMapper) {
        this.playListRepository = playListRepository;
        this.playListMapper = playListMapper;
        this.musicMapper = musicMapper;
    }


    @Override
    public void createNewPlaylist(PlayListRequestDto playListRequestDto) {
        playListRepository.save(playListMapper.toPlaylistModel(playListRequestDto));
    }

    @Override
    public void deletePlayListByID(Integer id) {
        playListRepository.deleteById(id);
    }

    @Override
    public void deletePlayListByName(String nameOfPlayList) {
        playListRepository.deleteByNameOfPlaylist(nameOfPlayList);
    }

    @Override
    public void addMusicToPlaylist(PlayListRequestDto playListRequestDto) {

        Optional<PlayList> playList1 = playListRepository.getByNameOfPlaylist(playListRequestDto.getNameOfPlaylist());
        if (playList1.isEmpty()) {
            throw new PlayListNotFoundException("PlayList not found");
        }
        PlayList playList = playList1.get();
        Music music = Music.builder()
                .name(playListRequestDto.getNameOfMusic())
                .singer(playListRequestDto.getMusicArtist())
                .id(playListRequestDto.getMusicId())
                .playList(playList)
                .build();
        playList.getMusicList().add(music);
        playListRepository.save(playList);
    }


    @Override
    public List<PlayListResponseDto> getAllPlayListOrder() {
        List<PlayList> playListList = playListRepository.findAll();
        if (playListList.isEmpty()) {
            throw new PlayListNotFoundException("Playlist not found");
        }
        return playListList.stream().map(playListMapper::playListResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<MusicResponseDto> getAllMusicsByPlaylistName(String nameOfPlaylist) {
        Optional<PlayList> playList = playListRepository.getByNameOfPlaylist(nameOfPlaylist);
        if (playList.isEmpty()){
            throw new PlayListNotFoundException("Playlist not found");
        }
        return playList.get().getMusicList().stream()
                .map(musicMapper::toMusicResponseDto).collect(Collectors.toList());
    }


    @Override
    public void deleteMusicFromPlaylist(PlayListRequestDto playListRequestDto) {
        Optional<PlayList> playList = playListRepository.getByNameOfPlaylist(playListRequestDto.getNameOfPlaylist());
        if (playList.isEmpty()){
            throw new PlayListNotFoundException("PlayList not found");
        }

        PlayList playlist = playList.get();
        playlist.getMusicList().removeIf(music -> music.getId()==(playListRequestDto.getMusicId()));
        playListRepository.save(playlist);
    }
}
