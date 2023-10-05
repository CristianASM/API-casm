package com.api.casm.Controller;

import com.api.casm.Model.User;
import com.api.casm.Service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> newUser(@Valid @RequestBody User user){
        User newUser = userService.newUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> userId(@PathVariable Long id){
        User userId = userService.getUserId(id);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> allUsers(){
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable Long id, @RequestBody User user){
        User updateuser = userService.updateUser(id, user);
        return new ResponseEntity<>(updateuser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Successfully deleted user", HttpStatus.OK);
    }
}
