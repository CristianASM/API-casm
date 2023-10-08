package com.api.casm.Service.ServiceImpl;

import com.api.casm.Dto.UserDTO;
import com.api.casm.Exceptions.UserNotFoundException;
import com.api.casm.Model.User;
import com.api.casm.Repository.UserRepository;
import com.api.casm.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO getUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO newUser(User user) {
        User savedUser = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        userRepository.save(existingUser);
        return modelMapper.map(existingUser, UserDTO.class);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.deleteById(id);
    }
}
