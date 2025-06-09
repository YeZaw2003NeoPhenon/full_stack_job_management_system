package com.example.job_management_system.security.user;


import com.example.job_management_system.security.user.dto.UserRecord;
import com.example.job_management_system.security.user.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final AppUserServiceImp appUserServiceImp;

    @Autowired
    public UserController(AppUserServiceImp appUserServiceImp) {
        this.appUserServiceImp = appUserServiceImp;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserRequest> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserServiceImp.createUser(userRequest));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserRecord>> allUsers(){
        return ResponseEntity.ok(appUserServiceImp.getAllUsers());
    }

}
