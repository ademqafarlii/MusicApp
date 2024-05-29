package com.example.musicApp.service;

import com.example.musicApp.dto.response.UserResponseDto;
import com.example.musicApp.dto.request.AddUserRequestDto;
import com.example.musicApp.dto.response.UserPageResponseDto;
public interface UserService {
    void update(AddUserRequestDto dto);

    void delete(Integer id);

    UserResponseDto getById(Integer id);

    UserPageResponseDto getAll(Integer page, Integer count);

    UserPageResponseDto getAllUsersInAscOrderByAge(Integer page, Integer count);

    UserPageResponseDto getAllUsersInAscOrderByID(Integer page, Integer count);

    UserPageResponseDto getAllUsersInDescOrderByAge(Integer page, Integer count);

    UserPageResponseDto getAllUsersInDescOrderByID(Integer page, Integer count);


}
