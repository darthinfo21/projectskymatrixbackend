package com.coforge.project.booking.feign;

import com.coforge.project.booking.dto.FlightDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "flight-service", url = "http://localhost:8081/api/flights")
public interface FlightFeignClient {
    @GetMapping("/{id}")
    FlightDTO getFlightById(@PathVariable Long id);

    @PutMapping("/update")
    void updateFlight(@RequestBody FlightDTO flight);
}