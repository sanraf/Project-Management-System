package com.algoExpert.demo.Mapper;

import com.algoExpert.demo.Dto.UserDto;
import com.algoExpert.demo.Entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;
    public User userDtoToUser(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

    public UserDto userToUserDto(User user){
        return modelMapper.map(user,UserDto.class);
    }

    public List<UserDto> usersToUserDtos(List<User> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
