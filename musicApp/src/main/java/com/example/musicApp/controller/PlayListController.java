package com.example.musicApp.controller;
import com.example.musicApp.dto.request.PlayListRequestDto;
import com.example.musicApp.dto.response.MusicResponseDto;
import com.example.musicApp.dto.response.PlayListResponseDto;
import com.example.musicApp.service.impl.PlayListServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@Slf4j
@RequestMapping("/a1/playlist")
@RestController
public class PlayListController {
    private final PlayListServiceImpl playListServiceImpl;

    public PlayListController(PlayListServiceImpl playListServiceImpl) {
        this.playListServiceImpl = playListServiceImpl;
    }


    @PostMapping("/create-new-playlist")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void createNewPlayList(@RequestBody @Valid PlayListRequestDto playListRequestDto) {
        log.info("playlist created");
        playListServiceImpl.createNewPlaylist(playListRequestDto);
    }

    @DeleteMapping("/delete-playlist-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deletePlayListByID(@PathVariable Integer id) {
        log.info("playlist deleted");
        playListServiceImpl.deletePlayListByID(id);
    }

    @DeleteMapping("/delete-playlist-by-name/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deletePlayListByName(@PathVariable String name) {
        log.info("playlist deleted");
        playListServiceImpl.deletePlayListByName(name);
    }


    @PostMapping("/add-music-to-playlist")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    void addMusicToPlaylist(PlayListRequestDto playListRequestDto) {
        log.info("music added");
        playListServiceImpl.addMusicToPlaylist(playListRequestDto);
    }

    @GetMapping("/get-all-playlists")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    List<PlayListResponseDto> getAllPlayListOrder() {
        return playListServiceImpl.getAllPlayListOrder();
    }

    @GetMapping("/get-all-music-from-playlist/{nameOfPlaylist}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    List<MusicResponseDto> getAllMusicsByPlaylistName(@PathVariable String nameOfPlaylist) {
        return playListServiceImpl.getAllMusicsByPlaylistName(nameOfPlaylist);
    }

    @DeleteMapping("/delete-music-from-playlist/{nameOfPlaylist}/{nameOfMusic}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void deleteMusicFromPlaylist(@RequestBody PlayListRequestDto playListRequestDto) {
        playListServiceImpl.deleteMusicFromPlaylist(playListRequestDto);
    }

}
