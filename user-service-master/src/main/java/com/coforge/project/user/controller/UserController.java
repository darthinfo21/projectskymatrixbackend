package com.coforge.project.user.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.project.user.model.User;
import com.coforge.project.user.repository.UserRepository;
import com.coforge.project.user.service.UserService;

@CrossOrigin(origins = "http://localhost:4200") // Replace with your frontend URL
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	
    private final UserService userService;
    private final UserRepository  userRepository;
   // private final AuthenticationManager authenticationManager;

    public UserController(UserService userService,UserRepository userRepository){//, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userRepository=userRepository;
      //  this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/updateWallet")
    public ResponseEntity<String> updateWallet(@RequestBody User user) {
        User updatedUser = userService.updateWallet(user);
        return ResponseEntity.ok("Wallet updated successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        String isAuthenticated = userService.login(user.getUsername(), user.getPassword());
        if (isAuthenticated.equals("User logged in successfully!")) {
            User loggedInUser = userRepository.findByUsername(user.getUsername());
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", loggedInUser.getId());
            response.put("authToken", "some-auth-token"); // Example token
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Invalid username or password"));
        }
    }
    
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User user) {
//        try {
//            String response = userService.loginUser(user.getUsername(), user.getPassword());
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    
    
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestBody User user) {
//        try {
//            String response = userService.loginUser(user.getUsername(), user.getPassword());
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}