package com.example.musicApp.mapper;

import com.example.musicApp.dto.request.AddUserRequestDto;
import com.example.musicApp.dto.response.UserResponseDto;
import com.example.musicApp.exception.UserNotFoundException;
import com.example.musicApp.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserResponseDto toUserDto(User user) {
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        return UserResponseDto.builder()
                .name(user.getName())
                .surName(user.getSurName())
                .userName(user.getUserName())
                .age(user.getAge())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .build();
    }

    public User toUserModel2(AddUserRequestDto addUserRequestDto) {
        if (addUserRequestDto == null) {
            throw new UserNotFoundException("User not found");
        }

        return User.builder()
                .name(addUserRequestDto.getName())
                .surName(addUserRequestDto.getSurName())
                .userName(addUserRequestDto.getUserName())
                .age(addUserRequestDto.getAge())
                .createdAt(addUserRequestDto.getCreatedAt())
                .createdBy(addUserRequestDto.getCreatedBy())
                .build();
    }
}
