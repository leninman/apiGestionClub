package com.sumadeportes.services;



import com.sumadeportes.model.dto.UserDto;
import com.sumadeportes.model.entities.UserEntity;

import java.util.Optional;

public interface IUserService {
     UserEntity saveUser(UserDto user);
     Optional<UserEntity> getUserByEmail(String email);
     UserEntity updateUser(UserDto user);
}
