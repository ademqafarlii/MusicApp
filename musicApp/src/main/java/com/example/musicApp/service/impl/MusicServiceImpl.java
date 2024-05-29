package com.example.musicApp.service.impl;
import com.example.musicApp.dto.request.MusicRequestDto;
import com.example.musicApp.dto.response.MusicPageResponseDto;
import com.example.musicApp.dto.response.MusicResponseDto;
import com.example.musicApp.exception.MusicAlreadyExistException;
import com.example.musicApp.exception.MusicNotFoundException;
import com.example.musicApp.exception.SingerNotFoundException;
import com.example.musicApp.mapper.MusicMapper;
import com.example.musicApp.model.Music;
import com.example.musicApp.repository.MusicRepository;
import com.example.musicApp.service.MusicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {
    private final MusicRepository musicRepository;
    private final MusicMapper musicMapper;

    public MusicServiceImpl(MusicRepository musicRepository, MusicMapper musicMapper) {
        this.musicRepository = musicRepository;
        this.musicMapper = musicMapper;
    }

    @Override
    public MusicResponseDto getById(Integer id) {
        return musicRepository.findById(id)
                .stream()
                .map(musicMapper::toMusicResponseDto)
                .findFirst()
                .orElseThrow(() -> new MusicNotFoundException("Music Not Found"));
    }

    @Override
    public void addNewMusicToLibrary(MusicRequestDto musicRequestDto) {
        List<Music> musicList = musicRepository.findAll();
        Music music = musicMapper.toMusicModel(musicRequestDto);
        if (musicList.contains(music)) {
            throw new MusicAlreadyExistException("Music already exist in library");
        }
        musicRepository.save(music);
    }

    @Override
    public void deleteMusicFromLibrary(Integer id) {
        List<Music> musicList = musicRepository.findAll();
        if (musicList.isEmpty()) {
            throw new MusicNotFoundException("Music not found");
        }
        musicRepository.deleteById(id);
    }

    @Override
    public MusicPageResponseDto getAllMusicsByAlphabetOrder(Integer page, Integer count) {
        Page<Music> musicPage = musicRepository.findAll(PageRequest.of(page, count));
        if (musicPage.isEmpty()) {
            throw new MusicNotFoundException("Your music library is empty");
        }
        return new MusicPageResponseDto(
                musicPage.getContent().stream()
                        .sorted(Comparator.comparing(Music::getName))
                        .map(musicMapper::toMusicResponseDto)
                        .toList()
                , musicPage.getTotalElements()
                , musicPage.getTotalPages()
                , musicPage.hasNext()
        );


    }

    @Override
    public MusicPageResponseDto getAllMusicsByReversedAlphabetOrder(Integer page, Integer count) {
        Page<Music> musicPage = musicRepository.findAll(PageRequest.of(page, count));

        if (musicPage.isEmpty()) {
            throw new MusicNotFoundException("Your music library is empty");
        }
        return new MusicPageResponseDto(
                musicPage.getContent().stream()
                        .sorted(Comparator.comparing(Music::getName).reversed())
                        .map(musicMapper::toMusicResponseDto)
                        .toList()
                , musicPage.getTotalElements()
                , musicPage.getTotalPages()
                , musicPage.hasNext()
        );
    }


    @Override
    public MusicPageResponseDto getAll(int page, int count) {
        Page<Music> musicPage = musicRepository.findAll(PageRequest.of(page, count));
        if (musicPage.isEmpty()) {
            throw new MusicNotFoundException("Your library is empty");
        }
        return new MusicPageResponseDto
                (musicPage.getContent()
                        .stream().map(musicMapper::toMusicResponseDto).toList()
                        , musicPage.getTotalElements()
                        , musicPage.getTotalPages()
                        , musicPage.hasNext());

    }

    @Override
    public MusicResponseDto findByName(String name) {

        return musicRepository.findByName(name)
                .stream()
                .map(musicMapper::toMusicResponseDto)
                .findFirst()
                .orElseThrow(() -> new MusicNotFoundException("Music not found"));

    }

    @Override
    public List<MusicResponseDto> findBySingerName(String name) {
        List<Music> list = musicRepository.findBySinger(name);
        if (list.isEmpty()){
            throw new SingerNotFoundException("Singer not found");
        }
        return musicRepository.findBySinger(name)
                .stream()
                .map(musicMapper::toMusicResponseDto)
                .toList();
    }


}
