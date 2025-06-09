package com.example.job_management_system.config;

import com.example.job_management_system.mapper.DataMapper;
import com.example.job_management_system.security.user.AppUser;
import com.example.job_management_system.security.user.dto.UserRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapper {


    @Bean
    public DataMapper<AppUser, UserRecord> usermapper(){
        return new DataMapper<AppUser, UserRecord>(
                user -> {
                    return new UserRecord(user.getUsername(), user.getEmail(), user.getRole());
                }
        );
    }
}