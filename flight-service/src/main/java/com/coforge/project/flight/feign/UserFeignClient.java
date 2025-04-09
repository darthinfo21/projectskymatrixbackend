package com.coforge.project.flight.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service-master", url = "http://localhost:8080/api/users")
public interface UserFeignClient {
    @GetMapping("/{id}")
    Object getUserById(@PathVariable Long id);
}
