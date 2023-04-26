package com.takeout.backend.controller;

import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class test {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/test/")
    public Map<String,String> test() {
        Map<String,String> map = new HashMap<>();
        map.put("error_message","success");
        return map;
    }
}
