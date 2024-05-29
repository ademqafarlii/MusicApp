package com.example.musicApp.controller;
import com.example.musicApp.dto.request.MusicRequestDto;
import com.example.musicApp.dto.response.MusicPageResponseDto;
import com.example.musicApp.dto.response.MusicResponseDto;
import com.example.musicApp.service.impl.MusicServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/a1/musics")
public class MusicController {
    private final MusicServiceImpl musicService;

    public MusicController(MusicServiceImpl musicService) {
        this.musicService = musicService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-music-by-id/{id}")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public MusicResponseDto getByID(@PathVariable Integer id) {
        log.info("getById request accepted");
        return musicService.getById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/add-music")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public void addNewMusicToLibrary(@RequestBody MusicRequestDto dto) {
        log.info("Music added");
        musicService.addNewMusicToLibrary(dto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete-music-by-id/{id}")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public void deleteMusicFromLibrary(@PathVariable Integer id) {
        log.info("Music deleted");
        musicService.deleteMusicFromLibrary(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-all-music")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public MusicPageResponseDto getAll(@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("request accepted");
        return musicService.getAll(page, count);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-all-musics-by-alphabet-order")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public MusicPageResponseDto getAllMusicsByAlphabetOrder
            (@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("request accepted");
        return musicService.getAllMusicsByAlphabetOrder(page, count);
    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-all-musics-by-reversed-alphabet-order")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public MusicPageResponseDto getAllMusicsByReversedAlphabetOrder
            (@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("request accepted");
        return musicService.getAllMusicsByReversedAlphabetOrder(page, count);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-music-by-name/{name}")
    @PreAuthorize("hasAnyRole('USER' , 'ADMIN')")
    public MusicResponseDto getMusicByName(@PathVariable String name) {
        log.info("request accepted");
        return musicService.findByName(name);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/find-by-singerName/{name}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<MusicResponseDto> findBySingerName(@PathVariable String name){
        log.info(name);
        return musicService.findBySingerName(name);
    }


}
