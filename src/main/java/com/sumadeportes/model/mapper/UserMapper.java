package com.sumadeportes.model.mapper;

import com.sumadeportes.model.dto.UserDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.UserEntity;


import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDto(
                userEntity.getUserId().getDocumentType(),
                userEntity.getUserId().getDocumentNumber(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthDate(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getLocked(),
                userEntity.getEnabled(),
                userEntity.getFirstTime(),
                userEntity.getNumberRetries(),
              //  userEntity.getRoles().stream()
                       // .map(RoleEntity::getName)
                        //.collect(Collectors.toList())
                userEntity.getRole()
        );
    }

    public static UserEntity toEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(new PersonId(userDto.getDocumentType(), userDto.getDocumentNumber()));
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setBirthDate(userDto.getBirthDate());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setLocked(userDto.getLocked());
        userEntity.setEnabled(userDto.getEnabled());
        userEntity.setFirstTime(userDto.getFirstTime());
        userEntity.setNumberRetries(userDto.getNumberRetries());
      //  userEntity.setRoles(userDto.getRoles().stream()
                //.map(roleName -> {
                  //  RoleEntity roleEntity = new RoleEntity();
                //    roleEntity.setName(roleName);
                //    return roleEntity;
              //  })
            //    .collect(Collectors.toList())
      //  );
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }


}
