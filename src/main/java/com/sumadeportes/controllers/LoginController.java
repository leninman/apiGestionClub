package com.sumadeportes.controllers;

import com.sumadeportes.model.dto.ChangePasswordRequestDto;
import com.sumadeportes.model.dto.LoginRequestDto;
import com.sumadeportes.model.dto.respDto;
import com.sumadeportes.model.entities.UserEntity;
import com.sumadeportes.model.mapper.UserMapper;
import com.sumadeportes.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://erika-github.github.io")
public class LoginController {
    private final IUserService userService;


    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        respDto response = new respDto();
        Optional<UserEntity> userEntity = userService.getUserByEmail(loginRequestDto.getEmail());
        if (!userEntity.isPresent()) {
            response.setCode("404");
            response.setMessage("User not found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        if (!userEntity.get().getPassword().equals(loginRequestDto.getPassword())) {
            response.setCode("401");
            response.setMessage("Invalid password");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        if(userEntity.get().getLocked()){
            response.setCode("403");
            response.setMessage("User is locked, please contact the administrator");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
        if(!userEntity.get().getEnabled()){
            response.setCode("403");
            response.setMessage("User is disabled, please contact the administrator");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
        if (userEntity.get().getFirstTime()) {
            response.setCode("403");
            response.setMessage("This is the first time you log in, please change your password");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        response.setCode("200");
        response.setMessage("Login successful");
        response.setData(userEntity.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> login(@RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
        respDto response = new respDto();
        Optional<UserEntity> userEntity = userService.getUserByEmail(changePasswordRequestDto.getEmail());

        if (!userEntity.get().getPassword().equals(changePasswordRequestDto.getOldPassword())) {
            response.setCode("401");
            response.setMessage("Invalid old password, please check your old password");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        userEntity.get().setPassword(changePasswordRequestDto.getNewPassword());
        userEntity.get().setFirstTime(false);
        userService.updateUser(UserMapper.toDto(userEntity.get()));
        response.setCode("200");
        response.setMessage("Password changed successfully");
        response.setData(userEntity.get());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
