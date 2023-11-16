package com.api.casm;

import com.api.casm.Dto.UserDTO;
import com.api.casm.Model.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestModelMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    @Test
    public void testUserToUserDTOMapping() {
        User userMock = new User(1L, "joaco", "jiji22e", "joaco@gmail.com", "asd1234567");
        UserDTO resultUserDTO = modelMapper.map(userMock, UserDTO.class);

        assertEquals(userMock.getId(), resultUserDTO.getId());
        assertEquals(userMock.getName(), resultUserDTO.getName());
        assertEquals(userMock.getUserName(), resultUserDTO.getUserName());
    }
}
