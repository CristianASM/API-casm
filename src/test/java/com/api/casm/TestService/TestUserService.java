package com.api.casm.TestService;

import com.api.casm.Dto.UserDTO;
import com.api.casm.Exceptions.UserNotFoundException;
import com.api.casm.Model.User;
import com.api.casm.Repository.UserRepository;
import com.api.casm.Service.ServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestUserService {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testGetUserId(){
        User user = new User(1L, "joaco", "jiji22e", "joaco@gmail.com", "asd1234567");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO userDTO = new UserDTO(1L, "joaco", "jiji22e");
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO resultUserDTO = userService.getUserId(1L);

        assertEquals(user.getId(), resultUserDTO.getId());
    }

    @Test
    public void testGetUserByUserName(){
        User user = new User(1L, "joaco", "jiji22e", "joaco@gmail.com", "asd1234567");
        when(userRepository.findUserByUserName("jiji22e")).thenReturn(user);

        UserDTO userDTO = new UserDTO(1L, "joaco", "jiji22e");
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO resultUserDTO = userService.getUserByUserName(userDTO.getUserName());

        assertEquals(user.getUserName(), resultUserDTO.getUserName());
    }

    @Test
    public void testNewUser(){
        User user = new User(1L, "joaco", "jiji22e", "joaco@gmail.com", "asd1234567");
        when(userRepository.save(user)).thenReturn(user);

        UserDTO userDTO = new UserDTO(1L, "joaco", "jiji22e");
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO resultUserDTO = userService.newUser(user);

        assertEquals(userDTO, resultUserDTO);
    }

    @Test
    public void testUpdateUser(){
        User user = new User(1L, "joaco", "jiji22e", "joaco@gmail.com", "asd1234567");
        User userUpdated = new User(1L, "mklli", "eoo344", "dwwe@gmail.com", "wwweeeeqqqq");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(userUpdated);

        UserDTO userDTO = new UserDTO(1L, "mklli", "eoo344");
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO resultUserDTO = userService.updateUser(1L,user);

        assertEquals(userDTO, resultUserDTO);
        assertNotEquals(user, userUpdated);
        assertEquals("mklli", userUpdated.getName());
    }

    @Test
    public void testDeleteUser(){
        User user = new User(1L, "joaco", "jiji22e", "joaco@gmail.com", "asd1234567");
        User userDeleted = new User();
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(userDeleted));
        assertTrue(userRepository.findById(userDeleted.getId()).isEmpty());
    }

    @Test
    public void testUserNotFound(){
        when(userRepository.findById(3L)).thenThrow(new UserNotFoundException("User Not Found"));
        assertThrows(UserNotFoundException.class, () -> userRepository.findById(4L));
    }
}
