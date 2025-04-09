package com.coforge.project.admin.service;

import org.springframework.stereotype.Service;

import com.coforge.project.admin.dto.FlightDTO;
import com.coforge.project.admin.feign.FlightClient;

@Service
public class AdminService {
    private final FlightClient flightFeignClient;

    public AdminService(FlightClient flightFeignClient) {
        this.flightFeignClient = flightFeignClient;
    }

    public String addFlight(FlightDTO flightDTO) {
        return flightFeignClient.addFlight(flightDTO);
    }

    public String deleteFlight(Long id) {
        return flightFeignClient.deleteFlight(id);
    }
}