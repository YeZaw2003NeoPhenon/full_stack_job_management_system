package com.example.job_management_system.mapper;

import com.example.job_management_system.security.user.AppUser;
import com.example.job_management_system.security.user.dto.UserRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserRecordMapper implements Function<AppUser, UserRecord>{

    @Override
    public UserRecord apply(AppUser appUser) {
        return new UserRecord(appUser.getUsername(), appUser.getEmail(), appUser.getRole());
    }

}