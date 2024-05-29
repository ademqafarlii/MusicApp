package com.example.musicApp.controller;

import com.example.musicApp.dto.request.ConnectionRequestDto;
import com.example.musicApp.dto.response.ConnectionResponseDto;
import com.example.musicApp.service.impl.ConnectionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/a1/connections")
@RestController
public class ConnectionController {

    private final ConnectionServiceImpl connectionService;

    public ConnectionController(ConnectionServiceImpl connectionService) {
        this.connectionService = connectionService;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/follow")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void follow(@RequestBody ConnectionRequestDto dto) {
        log.info("request accepted");
        connectionService.follow(dto);
    }

    @ResponseStatus
    @DeleteMapping("/unfollow")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public void unFollow(@RequestBody ConnectionRequestDto dto) {
        log.info("request accepted");
        connectionService.unFollow(dto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/followerList")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<ConnectionResponseDto> seeFollowerList() {
        log.info("request accepted");
        return connectionService.seeFollowerList();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-follower-by-id/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ConnectionResponseDto getFollowerById(@PathVariable Integer id) {
        log.info("request accepted");
        return connectionService.getFollowerById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-follower-by-name/{name}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ConnectionResponseDto getFollowerByName(@PathVariable String name) {
        log.info("request accepted");
        return connectionService.getFollowerByName(name);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-followList")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<ConnectionResponseDto> getFollowList(){
        log.info("request accepted");
        return connectionService.getFollowList();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-follow-by-id/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ConnectionResponseDto getFollowById(@PathVariable Integer id){
        log.info("request accepted");
        return connectionService.getFollowById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/get-follow-by-name/{name}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ConnectionResponseDto getFollowByName(@PathVariable String name){
        log.info("request accepted");
        return connectionService.getFollowByName(name);
    }
}
