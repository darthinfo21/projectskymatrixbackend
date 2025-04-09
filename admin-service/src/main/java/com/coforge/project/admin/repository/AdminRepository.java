package com.coforge.project.admin.repository;

import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin";

    public boolean authenticate(String username, String password) {
       return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
   }
}