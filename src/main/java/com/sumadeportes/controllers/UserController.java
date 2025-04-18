package com.sumadeportes.controllers;


import com.sumadeportes.model.dto.UserDto;
import com.sumadeportes.model.dto.RespDto;
import com.sumadeportes.model.entities.UserEntity;
import com.sumadeportes.services.EmailService;
import com.sumadeportes.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "https://erika-github.github.io")
public class UserController {


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    private final IUserService userService;
    private final EmailService emailService;


    public UserController(IUserService userService, EmailService emailService) {
        this.userService = userService;

        this.emailService = emailService;
    }

    @PostMapping("/create")
    public ResponseEntity<RespDto> saveUser(@RequestBody UserDto userDto) {
        RespDto response = new RespDto();
        try {
            Optional<UserEntity> userEntity = userService.getUserByEmail(userDto.getEmail());
            if (userEntity.isPresent()) {
                response.setMessage("Email already exists");
                response.setCode("409");
                response.setData(new ArrayList<>());
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            UserEntity savedUser = userService.saveUser(userDto);
            emailService.sendPasswordEmail(savedUser.getEmail(), savedUser.getPassword());
            response.setMessage("User saved successfully and email sent with password");
            response.setCode("201");
            response.setData(new ArrayList<>());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setMessage("Error saving user");
            response.setCode("500");
            response.setData(new ArrayList<>());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getByUser")
    public ResponseEntity<RespDto> getById(@RequestParam String email) {
        RespDto response = new RespDto();
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



    @PostMapping("/getAll")
    public ResponseEntity<RespDto> getAllTeams() {
        RespDto response = new RespDto();
        response.setMessage("Users found");
        response.setCode("200");
        response.setData(userService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespDto> handleException(Exception e) {
        RespDto response = new RespDto();
        response.setMessage(e.getMessage());
        response.setCode("500");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    public static int calculateAge(LocalDate bornDate) {
            return Period.between(bornDate, LocalDate.now()).getYears();
    }





}
