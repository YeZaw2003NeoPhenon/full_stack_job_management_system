package com.example.job_management_system.security.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Unauthorized"));
        }
//        String username = authentication.getName();
        return ResponseEntity.ok(Map.of("user principal", authentication.getPrincipal()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request , HttpServletResponse response) {

       HttpSession session = request.getSession(false);

       if(session != null){
           session.invalidate();
       }
       logger.info("User is logged out");
       SecurityContextHolder.clearContext();
       Cookie cookie = new Cookie("JSESSIONID", null);
       cookie.setMaxAge(0);
       response.addCookie(cookie);
       response.setStatus(HttpServletResponse.SC_OK);
       return ResponseEntity.ok(Map.of("message", "logout successfully"));
    }

}
