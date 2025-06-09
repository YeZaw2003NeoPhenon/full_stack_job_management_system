package com.example.job_management_system.security.user.dto;

import com.example.job_management_system.security.user.Role;

public record UserRecord(String username, String email, Role role) {
}
