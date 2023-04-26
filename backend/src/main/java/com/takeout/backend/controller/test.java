package com.takeout.backend.controller;

import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class test {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/test/")
    public String test() {

        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "sucess";
    }
}
