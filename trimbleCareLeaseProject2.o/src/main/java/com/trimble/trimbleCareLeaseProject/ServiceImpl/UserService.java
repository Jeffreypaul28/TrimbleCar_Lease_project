package com.trimble.trimbleCareLeaseProject.ServiceImpl;

import com.trimble.trimbleCareLeaseProject.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    void deleteUser(Long id);
}
