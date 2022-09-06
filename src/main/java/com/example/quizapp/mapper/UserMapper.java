package com.example.quizapp.mapper;


import com.example.quizapp.DTO.UserEditDTO;
import com.example.quizapp.models.User;
import com.example.quizapp.DTO.UserDetailResponseDTO;
import com.example.quizapp.DTO.UserResponseDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = SharedConfig.class)
public interface UserMapper {
    @Named(value = "userResponseDTO")
    UserResponseDTO map(User user);

    UserDetailResponseDTO mapDetail(User user);

    @IterableMapping(qualifiedByName = "userResponseDTO")
    List<UserResponseDTO> mapList(List<User> user);


    void merge(@MappingTarget User user, UserEditDTO userUpdateDTO);

}