package com.api.casm.Controller;

import com.api.casm.Model.User;
import com.api.casm.Service.ServiceImpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "Request error")
    public ResponseEntity<User> newUser(@Valid @RequestBody User user){
        User newUser = userService.newUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    @ApiResponse(responseCode = "200", description = "User found successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<User> userId(@PathVariable Long id){
        User userId = userService.getUserId(id);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a user by ID")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "400", description = "Request error")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<User> updateUser(@Valid @PathVariable Long id, @RequestBody User user){
        User updateuser = userService.updateUser(id, user);
        return new ResponseEntity<>(updateuser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    @ApiResponse(responseCode = "200", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Successfully deleted user", HttpStatus.OK);
    }
}
