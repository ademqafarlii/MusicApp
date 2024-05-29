package com.example.musicApp.service.impl;
import com.example.musicApp.dto.request.ConnectionRequestDto;
import com.example.musicApp.dto.response.ConnectionResponseDto;
import com.example.musicApp.exception.AccountNotFoundException;
import com.example.musicApp.exception.FollowNotFoundException;
import com.example.musicApp.exception.FollowerAlreadyExistException;
import com.example.musicApp.exception.FollowerNotFoundException;
import com.example.musicApp.mapper.ConnectionMapper;
import com.example.musicApp.model.Connection;
import com.example.musicApp.repository.ConnectionRepository;
import com.example.musicApp.service.ConnectionService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final ConnectionMapper connectionMapper;

    public ConnectionServiceImpl(ConnectionRepository connectionRepository, ConnectionMapper connectionMapper) {
        this.connectionRepository = connectionRepository;
        this.connectionMapper = connectionMapper;
    }

    @Override
    public void follow(ConnectionRequestDto dto) {
        List<Connection> connectionList = connectionRepository.findAll();
        Connection connectionRequestDto = connectionMapper.toConnectionModel(dto);
        if (connectionList.contains(connectionRequestDto)) {
            throw new FollowerAlreadyExistException("Follower already exist");
        }
        connectionRepository.save(connectionRequestDto);
    }

    @Override
    public void unFollow(ConnectionRequestDto dto) {
        List<Connection> connectionList = connectionRepository.findAll();
        if (connectionList.isEmpty()) {
            throw new FollowerNotFoundException("Follower not found");
        }
        connectionRepository.delete(connectionMapper.toConnectionModel(dto));
    }

    @Override
    public List<ConnectionResponseDto> seeFollowerList() {
        List<Connection> connectionList = connectionRepository.findAll();
        if (connectionList.isEmpty()) {
            throw new FollowerNotFoundException("You have no followers");
        }
        return connectionRepository.findAll()
                .stream()
                .map(connectionMapper::toConnectionResponseDto)
                .toList();
    }

    @Override
    public ConnectionResponseDto getFollowerById(Integer id) {
        Optional<Connection> connectionList = connectionRepository.findById(id);
        if (connectionList.isEmpty()) {
            throw new FollowerNotFoundException("You have 0 follower");
        }
        return connectionRepository.findById(id)
                .stream()
                .map(connectionMapper::toConnectionResponseDto)
                .findFirst()
                .orElseThrow(() -> new FollowerNotFoundException("Follower not found"));
    }

    @Override
    public ConnectionResponseDto getFollowerByName(String name) {
        Optional<Connection> connection = connectionRepository.getByUserName(name);
        if (connection.isEmpty()) {
            throw new AccountNotFoundException("Account not found");
        }
        return connectionRepository.getByUserName(name)
                .stream()
                .map(connectionMapper::toConnectionResponseDto)
                .findFirst()
                .orElseThrow(()->new FollowNotFoundException("Account not found"));

    }

    @Override
    public List<ConnectionResponseDto> getFollowList() {
        List<Connection> followList = connectionRepository.findAll();
        if (followList.isEmpty()){
            throw new FollowNotFoundException("Account not found");
        }
        return connectionRepository.findAll()
                .stream()
                .map(connectionMapper::toConnectionResponseDto)
                .toList();
    }


    @Override
    public ConnectionResponseDto getFollowById(Integer ID) {
        Optional<Connection> follow = connectionRepository.findById(ID);
        if (follow.isEmpty()){
            throw new FollowNotFoundException("Account not found");
        }
        return connectionRepository.findById(ID)
                .stream()
                .map(connectionMapper::toConnectionResponseDto)
                .findFirst()
                .orElseThrow(()-> new FollowNotFoundException("Account not found"));
    }

    @Override
    public ConnectionResponseDto getFollowByName(String name) {
        Optional<Connection> connectionList = connectionRepository.getByUserName(name);
        if (connectionList.isEmpty()){
            throw new FollowNotFoundException("Account not found");
        }
        return connectionRepository.getByUserName(name)
                .stream()
                .map(connectionMapper::toConnectionResponseDto)
                .findFirst()
                .orElseThrow(()->new FollowNotFoundException("Account not found"));
    }

}
