package com.coforge.project.seatselection.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private double walletBalance;
}