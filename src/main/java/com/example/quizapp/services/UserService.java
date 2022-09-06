package com.example.quizapp.services;

import com.example.quizapp.DTO.UserCreationDTO;
import com.example.quizapp.DTO.UserEditDTO;
import com.example.quizapp.DTO.UserResponseDTO;
import com.example.quizapp.models.User;
import com.example.quizapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
       this.userRepository = userRepository ;
    }

    public User dtoToUser(UserCreationDTO userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        return user;
    }
    @Transactional
    public void create(User user) {
        Optional<User> studentByEmail =
                userRepository.findUserByEmail(user.getEmail());
        user = userRepository.save(user);
        System.out.println(user.toString() + "is successfully added");
    }
    public UserResponseDTO userToResponseDTO(User user){
        UserResponseDTO userDto = new UserResponseDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        return userDto;
    }

    public List<UserResponseDTO> get() {
        return userRepository.findAll().stream().map(this::userToResponseDTO).collect(Collectors.toList());
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
        System.out.println("User is successfully deleted");
    }


    @Transactional
    public void edit(long id, UserEditDTO userEditDTO) {
        String name = userEditDTO.getName();
        String lastName = userEditDTO.getLastName();
        String email = userEditDTO.getEmail();
        System.out.println(email);
        String password = userEditDTO.getPassword();
        User user = userRepository.findById(id)
                .orElseThrow(() ->new IllegalStateException("There is no student with id "+ id));
        if(name != null && name.length() != 0 && !Objects.equals(name, user.getName())){
            user.setName(name);
        }
        if(lastName != null && lastName.length() != 0 && !Objects.equals(lastName, user.getLastName())){
            user.setLastName(lastName);
        }
        if(password != null && password.length() != 0 && !Objects.equals(password, user.getPassword())){
            user.setPassword(password);
        }
        if(email != null && email.length() != 0 && !Objects.equals(email, user.getEmail())){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("the email has already taken");
            }
            user.setEmail(email);
        }
        userRepository.save(user);
        System.out.println("User is successfully edited");
    }

    public UserResponseDTO getUser(long id) {
        return userToResponseDTO(userRepository.findById(id).orElseThrow(()->
                new IllegalStateException("User with ID "+id+"does not exist")));
    }
}
