package com.test.splitwise.controller;

import com.test.splitwise.model.dto.UserDTO;
import com.test.splitwise.model.entity.User;
import com.test.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody UserDTO userDTO) {
    User newUser = userService.onboardUser(userDTO);
    return ResponseEntity.ok(newUser);
  }

  @PutMapping("/{userId}/change-password")
  public ResponseEntity<String> changePassword(@PathVariable Integer userId,
      @RequestParam String newPassword) {
    userService.changePassword(userId, newPassword);
    return ResponseEntity.ok("Password changed successfully.");
  }
}

