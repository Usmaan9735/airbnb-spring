package com.airbnb2.service;

import com.airbnb2.Dto.LoginDto;
import com.airbnb2.Dto.PropertyUserDto;
import com.airbnb2.entity.PropertyUser;
import com.airbnb2.repo.PropertyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PropertyUserRepository propertyUserRepository;
    private final JWTService jwtService;  // Declare jwtService

    // Constructor with both PropertyUserRepository and JWTService
    public UserService(PropertyUserRepository propertyUserRepository, JWTService jwtService) {
        this.propertyUserRepository = propertyUserRepository;
        this.jwtService = jwtService;  // Initialize jwtService
    }

    // Method to add a new user
    public PropertyUser addUser(PropertyUserDto propertyUserDto) {
        PropertyUser user = new PropertyUser();
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setUserName(propertyUserDto.getUserName());  // Setting the userName
        user.setEmail(propertyUserDto.getEmail());
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(), BCrypt.gensalt(10)));
        user.setUserRole(propertyUserDto.getUserRole());

        // Save the user to the database
        PropertyUser savedUser = propertyUserRepository.save(user);
        return savedUser;
    }

    // Method to verify user login and return JWT token if successful
    public String verifyLogin(LoginDto loginDto) {
        // Find the user by username
        Optional<PropertyUser> opUser = propertyUserRepository.findByUserName(loginDto.getUsername());

        if (opUser.isPresent()) {
            PropertyUser propertyUser = opUser.get();

            // Check if the password matches the one in the database
            if (BCrypt.checkpw(loginDto.getPassword(), propertyUser.getPassword())) {
                // If passwords match, generate and return the JWT token
                return jwtService.generateToken(propertyUser.getUserName());
            }
        }

        // If authentication fails, return null (or you could throw an exception here)
        return null;
    }
}
