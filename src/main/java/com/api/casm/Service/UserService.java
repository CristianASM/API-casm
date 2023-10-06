package com.api.casm.Service;

import com.api.casm.Model.User;

import java.util.List;

public interface UserService {
    User getUserId(Long id);
    User newUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
