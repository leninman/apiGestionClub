package com.sumadeportes.controllers;


import com.sumadeportes.model.dto.UserDto;
import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.UserEntity;
import com.sumadeportes.services.EmailService;
import com.sumadeportes.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "https://erika-github.github.io")
public class UserController {

    private final IUserService userService;
    private final EmailService emailService;


    public UserController(IUserService userService, EmailService emailService) {
        this.userService = userService;

        this.emailService = emailService;
    }

    @PostMapping("/create")
    public ResponseEntity<respDto> saveUser(@RequestBody UserDto userDto) {
        respDto response = new respDto();
        try {
            Optional<UserEntity> userEntity = userService.getUserByEmail(userDto.getEmail());
            if (userEntity.isPresent()) {
                response.setMessage("Email already exists");
                response.setCode("409");
                response.setData(userEntity);
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            UserEntity savedUser = userService.saveUser(userDto);
            emailService.sendPasswordEmail(savedUser.getEmail(), savedUser.getPassword());
            response.setMessage("User saved successfully and email sent with password");
            response.setCode("201");
            response.setData(savedUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage("Error saving user");
            response.setCode("500");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getByUser")
    public ResponseEntity<respDto> getById(@RequestParam String email) {
        respDto response = new respDto();
        try {
            Optional<UserEntity> user = userService.getUserByEmail(email);
            response.setMessage("User found");
            response.setCode("200");
            response.setData(user);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("User not found");
            response.setCode("404");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }



    @GetMapping("/getAll")
    public ResponseEntity<respDto> getAllTeams() {
        respDto response = new respDto();
        response.setMessage("Users found");
        response.setCode("200");
        response.setData(userService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<respDto> handleException(Exception e) {
        respDto response = new respDto();
        response.setMessage(e.getMessage());
        response.setCode("500");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
