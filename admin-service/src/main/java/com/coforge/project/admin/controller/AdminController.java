package com.coforge.project.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coforge.project.admin.dto.FlightDTO;
import com.coforge.project.admin.repository.AdminRepository;
import com.coforge.project.admin.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200") // Replace with your frontend URL
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final AdminRepository adminRepository;

    public AdminController(AdminService adminService, AdminRepository adminRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (adminRepository.authenticate(username, password)) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials!");
        }
    }
    

    @PostMapping("/addFlight")
    public ResponseEntity<String> addFlight(@RequestBody FlightDTO flightDTO) {
        return ResponseEntity.ok(adminService.addFlight(flightDTO));
    }

    @DeleteMapping("/deleteFlight/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.deleteFlight(id));
    }
}