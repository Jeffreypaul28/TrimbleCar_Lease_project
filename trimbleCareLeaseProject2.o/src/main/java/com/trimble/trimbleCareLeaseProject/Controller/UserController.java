package com.trimble.trimbleCareLeaseProject.Controller;

import com.trimble.trimbleCareLeaseProject.DTO.UserDTO;
import com.trimble.trimbleCareLeaseProject.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable Long id) {
        Optional<UserDTO> user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Update a user by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO updatedUserDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(id, updatedUserDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Or return a custom error message
        }
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
