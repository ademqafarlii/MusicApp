package com.example.musicApp.mapper;
import com.example.musicApp.dto.request.ConnectionRequestDto;
import com.example.musicApp.dto.response.ConnectionResponseDto;
import com.example.musicApp.exception.ConnectionNotFoundException;
import com.example.musicApp.model.Connection;
import org.springframework.stereotype.Service;

@Service
public class ConnectionMapper {
    public ConnectionResponseDto toConnectionResponseDto(Connection connection) {
        if (connection == null) {
           throw new ConnectionNotFoundException("Connection not found");
        }
        ConnectionResponseDto connectionResponseDto = new ConnectionResponseDto();
        connectionResponseDto.setId(connection.getId());
        connectionResponseDto.setUserNameForFollow(connection.getUserName());
        return connectionResponseDto;
    }

    public Connection toConnectionModel(ConnectionRequestDto dto) {
        if (dto == null) {
            throw new ConnectionNotFoundException("Connection not found");
        }
        return Connection.builder()
                .id(dto.getId())
                .countOfFollow(dto.getCountOfFollow())
                .userName(dto.getUserName())
                .countOfFollower(dto.getCountOfFollower())
                .build();
    }


}
