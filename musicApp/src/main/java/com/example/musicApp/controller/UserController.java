package com.example.musicApp.controller;

import com.example.musicApp.dto.request.AddUserRequestDto;
import com.example.musicApp.dto.response.UserResponseDto;
import com.example.musicApp.dto.response.UserPageResponseDto;
import com.example.musicApp.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/a1/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void update(@RequestBody AddUserRequestDto userDto) {
        log.info("Update request accepted");
        userService.update(userDto);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void delete(@PathVariable Integer id) {
        log.info("Delete request accepted");
        userService.delete(id);
    }

    @GetMapping("/get-user-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserResponseDto getById(@PathVariable Integer id) {
        log.info("Request Accepted");
        return userService.getById(id);
    }

    @GetMapping("/get-all")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserPageResponseDto getAll(@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("Request accepted");
        return userService.getAll(page, count);
    }

    @GetMapping("/get-all-users-in-acs-order-by-age")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserPageResponseDto getAllUsersInAscOrderByAge(@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("Request accepted");
        return userService.getAllUsersInAscOrderByAge(page, count);
    }

    @GetMapping("/get-all-users-in-acs-order-by-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserPageResponseDto getAllUsersInAscOrderByID(@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("Request accepted");
        return userService.getAllUsersInAscOrderByID(page, count);
    }


    @GetMapping("/get-all-users-in-desc-order-by-age")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserPageResponseDto getAllUsersInDescOrderByAge(@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("Request accepted");
        return userService.getAllUsersInDescOrderByAge(page, count);
    }

    @GetMapping("/get-all-users-in-desc-order-by-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserPageResponseDto getAllUsersInDescOrderByID(@RequestParam(value = "page") Integer page, @RequestParam(value = "count") Integer count) {
        log.info("Request accepted");
        return userService.getAllUsersInDescOrderByID(page, count);
    }

}
