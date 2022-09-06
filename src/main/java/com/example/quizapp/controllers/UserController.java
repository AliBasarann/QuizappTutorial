package com.example.quizapp.controllers;

import com.example.quizapp.DTO.UserCreationDTO;
import com.example.quizapp.DTO.UserEditDTO;
import com.example.quizapp.DTO.UserResponseDTO;
import com.example.quizapp.models.User;
import com.example.quizapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;
    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public void add(@RequestBody UserCreationDTO userDTO){
        userService.create(userDTO);
    }

    @GetMapping
    public List<UserResponseDTO> get(){
        return userService.get();
    }

    @GetMapping(path="/{userId}")
    public UserResponseDTO getUser(@PathVariable("userId") long id){
        return userService.getUser(id);
    }

    @DeleteMapping(path = "/{userId}")
    public void delete(@PathVariable ("userId") long id){
        userService.delete(id);
    }

    @PutMapping(path = "/{userId}")
    public void edit(@PathVariable("userId") long id,
                     @RequestBody UserEditDTO userEditDTO){
        userService.edit(id, userEditDTO);
    }
}
