package com.trimble.trimbleCareLeaseProject.ServiceImpl;

import com.trimble.trimbleCareLeaseProject.DTO.UserDTO;
import com.trimble.trimbleCareLeaseProject.Model.User;
import com.trimble.trimbleCareLeaseProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(this::mapToDTO);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();

            // Update fields
            existingUser.setUsername(updatedUserDTO.getUsername());
            existingUser.setPassword(updatedUserDTO.getPassword());
            existingUser.setMobileNumber(updatedUserDTO.getMobileNumber());
            existingUser.setEmail(updatedUserDTO.getEmail());
            existingUser.setRole(User.UserRole.valueOf(updatedUserDTO.getRole()));

            User updatedUser = userRepository.save(existingUser);
            return mapToDTO(updatedUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    // Mapping methods
    private UserDTO mapToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getRole().toString()
        );
    }

//    private User mapToEntity(UserDTO userDTO) {
//        return new User(
//                userDTO.getId(),
//                userDTO.getUsername(),
//                userDTO.getPassword(),
//                userDTO.getMobileNumber(),
//                userDTO.getEmail(),
//                User.UserRole.valueOf(userDTO.getRole()),
//                null // Lease history not included in DTO
//        );
//    }
    
    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEmail(userDTO.getEmail());
        try {
            user.setRole(User.UserRole.valueOf(userDTO.getRole().toUpperCase())); // Ensures case-insensitive matching
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role provided: " + userDTO.getRole());
        }
        return user;
    }

}
