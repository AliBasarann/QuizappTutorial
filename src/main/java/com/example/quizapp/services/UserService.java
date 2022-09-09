package com.example.quizapp.services;

import com.example.quizapp.DTO.UserCreationDTO;
import com.example.quizapp.DTO.UserEditDTO;
import com.example.quizapp.DTO.UserResponseDTO;
import com.example.quizapp.mapper.UserMapper;
import com.example.quizapp.models.User;
import com.example.quizapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDTO create(UserCreationDTO userInsertDTO) {
        User user = User.builder()
                .name(userInsertDTO.getName())
                .lastName(userInsertDTO.getLastName())
                .username(userInsertDTO.getUsername())
                .password(userInsertDTO.getPassword())
                .build();
        return userMapper.map(userRepository.save(user));

    }
    public UserResponseDTO userToResponseDTO(User user){
        UserResponseDTO userDto = new UserResponseDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    public List<UserResponseDTO> get() {
        return userMapper.mapList(userRepository.findAll());
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
        System.out.println("User is successfully deleted");
    }


    @Transactional
    public void edit(long id, UserEditDTO userEditDTO) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Wrong userId"));
        userMapper.merge(user, userEditDTO);
        userRepository.save(user);
        System.out.println("User is successfully edited");
    }

    public UserResponseDTO getUser(long id) {
        return userMapper.mapDetail(userRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Wrong userId")));
    }

}
