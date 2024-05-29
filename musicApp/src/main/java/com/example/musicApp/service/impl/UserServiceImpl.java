package com.example.musicApp.service.impl;

import com.example.musicApp.dto.response.UserResponseDto;
import com.example.musicApp.dto.request.AddUserRequestDto;
import com.example.musicApp.dto.response.UserPageResponseDto;
import com.example.musicApp.exception.IdAlreadyExistException;
import com.example.musicApp.exception.UserNotFoundException;
import com.example.musicApp.mapper.UserMapper;
import com.example.musicApp.model.User;
import com.example.musicApp.repository.UserRepository;
import com.example.musicApp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public void update(AddUserRequestDto dto) {
        Optional<User> existingId = userRepository.findById(dto.getId());
        if (existingId.isPresent()) {
            throw new IdAlreadyExistException("This ID already exist");
        } else {
            userRepository.save(mapper.toUserModel2(dto));
        }
    }

    @Override
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("There is no such ID"));
        userRepository.delete(user);

    }

    @Override
    public UserResponseDto getById(Integer id) {
        return userRepository.findById(id)
                .stream()
                .map(mapper::toUserDto)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("There is no such ID"));
    }

    @Override
    public UserPageResponseDto getAll(Integer page, Integer count) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, count));
        return new UserPageResponseDto
                (userPage.getContent().stream().map(mapper::toUserDto).toList()
                        , userPage.getTotalElements()
                        , userPage.getTotalPages()
                        , userPage.hasNext());
    }

    @Override
    public UserPageResponseDto getAllUsersInAscOrderByAge(Integer page, Integer count) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, count));
        return new UserPageResponseDto
                (userPage.getContent().stream().sorted(Comparator.comparing(User::getAge)
                                .thenComparing(User::getName))
                        .map(mapper::toUserDto)
                        .toList()
                        , userPage.getTotalElements()
                        , userPage.getTotalPages()
                        , userPage.hasNext());
    }

    @Override
    public UserPageResponseDto getAllUsersInAscOrderByID(Integer page, Integer count) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, count));
        return new UserPageResponseDto
                (userPage.getContent().stream().sorted(Comparator.comparing(User::getId).thenComparing(User::getName))
                        .map(mapper::toUserDto)
                        .toList()
                        , userPage.getTotalElements()
                        , userPage.getTotalPages()
                        , userPage.hasNext());
    }


    @Override
    public UserPageResponseDto getAllUsersInDescOrderByID(Integer page, Integer count) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, count));
        return new UserPageResponseDto
                (userPage.getContent().stream().sorted(Comparator.comparing(User::getId).reversed().thenComparing(User::getName))
                        .map(mapper::toUserDto)
                        .toList()
                        , userPage.getTotalElements()
                        , userPage.getTotalPages()
                        , userPage.hasNext());
    }

    @Override
    public UserPageResponseDto getAllUsersInDescOrderByAge(Integer page, Integer count) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, count));
        return new UserPageResponseDto
                (userPage.getContent().stream().sorted(Comparator.comparing(User::getAge).reversed().thenComparing(User::getName))
                        .map(mapper::toUserDto)
                        .toList()
                        , userPage.getTotalElements()
                        , userPage.getTotalPages()
                        , userPage.hasNext());
    }

}
