package com.coforge.project.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.coforge.project.admin.dto.FlightDTO;

@FeignClient(name = "flight-service", url = "http://localhost:8081/api/flights")
public interface FlightClient {
    @PostMapping("/add")
    String addFlight(@RequestBody FlightDTO flightDTO);

    @DeleteMapping("/{id}")
    String deleteFlight(@PathVariable Long id);
    
}
