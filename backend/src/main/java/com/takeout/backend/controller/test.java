package com.takeout.backend.controller;

import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class test {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/test/")
    public String test(@RequestParam Map<String,String> data) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(data.get("open_id"));
        if(userDetails != null) {
            System.out.println("");
        }
        return "未注册";
    }
}
