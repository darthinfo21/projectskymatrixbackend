package com.coforge.project.booking.feign;

import com.coforge.project.booking.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service-master", url = "http://localhost:8080/api/users")
public interface UserFeignClient {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @PutMapping("/update")
    void updateUser(@RequestBody UserDTO user);
}