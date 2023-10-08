package com.api.casm.Service;

import com.api.casm.Dto.UserDTO;
import com.api.casm.Model.User;

public interface UserService {
    UserDTO getUserId(Long id);
    UserDTO getUserByUserName(String userName);
    UserDTO newUser(User user);
    UserDTO updateUser(Long id, User user);
    void deleteUser(Long id);
}
