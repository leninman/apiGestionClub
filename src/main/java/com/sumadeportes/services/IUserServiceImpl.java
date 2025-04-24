package com.sumadeportes.services;


import com.sumadeportes.utils.Utils;
import com.sumadeportes.model.dto.UserDto;
import com.sumadeportes.model.entities.PersonId;
import com.sumadeportes.model.entities.UserEntity;
import com.sumadeportes.model.mapper.UserMapper;
import com.sumadeportes.model.repositories.SwimmerRepository;
import com.sumadeportes.model.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class IUserServiceImpl implements IUserService {


    private final UserRepository userRepository;
    private final SwimmerRepository swimmerRepository;


    public IUserServiceImpl(UserRepository userRepository, SwimmerRepository swimmerRepository) {
        this.userRepository = userRepository;
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public UserEntity saveUser(UserDto userDto) {

        LocalDate birthDate = userDto.getBirthDate();

        UserEntity userEntity = UserMapper.toEntity(userDto);
        userEntity.setRole(userDto.getRole());
        // Generate and set the password
        String generatedPassword = Utils.generateRandomPassword(8);
        userEntity.setPassword(generatedPassword);
        int swimmerAge = calculateAge(birthDate);
        if (swimmerAge < 18) {
            List<String> numberDocs = getNumDocs(userDto.getDocumentNumber());
            String docNumber="";
            if(numberDocs.isEmpty()){
                docNumber= userDto.getDocumentNumber().concat("-").concat("1");
            }else{
                int biggestNumber = numberDocs.stream()
                        .mapToInt(num -> Integer.parseInt(num.split("-")[1]))
                        .max()
                        .orElse(0);
                // Increment the biggest number by 1
                biggestNumber = biggestNumber + 1;
                docNumber = userDto.getDocumentNumber().concat("-").concat(String.valueOf(biggestNumber + 1));
            }
            userEntity.getUserId().setDocumentNumber(docNumber);
        }
        // Save the userEntity
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateUser(UserDto user) {
        UserEntity updatedUser;
        PersonId personId = new PersonId(user.getDocumentType(), user.getDocumentNumber());
        Optional<UserEntity> optionalUserEntity = userRepository.findById(personId);

        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userEntity.setPassword(user.getPassword());
            userEntity.setRole(user.getRole());
            userRepository.save(userEntity);
        } else {
            // Handle the case where the user is not found
            throw new RuntimeException("User not found");
        }

    }

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }

    public int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public List<String> getNumDocs(String numDoc) {
        return swimmerRepository.findDocumentNumberBySwimmerId(numDoc);
    }
}
