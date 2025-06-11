package com.example.admin.controller;

import com.example.admin.model.dto.TokenDto;
import com.example.admin.model.dto.UserAllDto;
import com.example.admin.model.dto.UserDto;
import com.example.admin.model.dto.UserLoginPasswordDto;
import com.example.admin.model.entity.User;
import com.example.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public void registration(@RequestBody UserAllDto userAllDto) {
        userService.registration(userAllDto);
    }

    @GetMapping("/autorization")
    public String autorization(@RequestBody UserLoginPasswordDto userLoginPasswordDto) {
        return userService.autorization(userLoginPasswordDto);
    }

    @GetMapping("/validToken")
    public boolean validToken(@RequestBody TokenDto tokenDto) {
        return userService.validToken(tokenDto);
    }

    @GetMapping("/getToken")
    public String getToken(@RequestHeader("token") String token) {
        System.out.println(token);
        return null;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable("id") UUID id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @GetMapping("/findByEmail/{email}")
    public User findByEmail(@PathVariable("email") UserDto userDto) {
        return userService.findByEmail(userDto);
    }

    @GetMapping("/getUsersForMonth/{endDataTime}")
    public List<User> getUsersForMonth(@PathVariable("endDataTime") LocalDateTime endDataTime) {
        return userService.getUsersForMonth(endDataTime);
    }
}
