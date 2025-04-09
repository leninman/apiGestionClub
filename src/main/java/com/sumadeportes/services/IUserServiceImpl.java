package com.sumadeportes.services;


import com.sumadeportes.Utils;
import com.sumadeportes.model.dto.UserDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.UserEntity;
import com.sumadeportes.model.mapper.UserMapper;
import com.sumadeportes.model.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IUserServiceImpl implements IUserService {


    private final UserRepository userRepository;


    public IUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveUser(UserDto userDto) {
        UserEntity userEntity = UserMapper.toEntity(userDto);
        userEntity.setRole(userDto.getRole());
        // Generate and set the password
        String generatedPassword = Utils.generateRandomPassword(8);
        userEntity.setPassword(generatedPassword);
        // Save the userEntity
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity updateUser(UserDto user) {
        UserEntity updatedUser;
        PersonId personId = new PersonId(user.getDocumentType(), user.getDocumentNumber());
        Optional<UserEntity> optionalUserEntity = userRepository.findById(personId);

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setPassword(user.getPassword());
          //  List<RoleEntity> roles = user.getRoles().stream()
                  //  .map(roleRepository::findRoleByName)
                  //  .collect(Collectors.toList());
           // userEntity.setRoles(roles);
            userEntity.setRole(user.getRole());
            updatedUser = userRepository.save(userEntity);
        } else {
            // Handle the case where the user is not found
            throw new RuntimeException("User not found");
        }
        return updatedUser;

    }

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }
}
