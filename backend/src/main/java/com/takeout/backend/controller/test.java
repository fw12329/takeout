package com.takeout.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class test {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/test/")
    public String test(@RequestBody Map<Object,Object> data) {
        List<Map<String, String>> list = (List<Map<String, String>>) data.get("map");
        Map<String, String> result = list.get(0);
        System.out.println(data.get("seller_id"));
        System.out.println(result);

        return "success";
    }
}
