package com.takeout.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.backend.mapper.UserMapper;
import com.takeout.backend.pojo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper mapper;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        BufferedReader reader = null;
        String line = null;
        Map<Object,Object> map = null;
        String openId;
        String number;
        try {

            reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {

                stringBuilder.append(line);
            }

            String requestBody = stringBuilder.toString();

            map = mapper.readValue(requestBody, Map.class);
            System.out.println(map);
            openId = map.get("open_id").toString();
            number = map.get("number").toString();

            UserDetails userDetails = userDetailsService.loadUserByUsername(openId);
            if(userDetails == null) {
                String username = "QS" + RandomStringUtils.randomNumeric(8);
                User user = new User(null,username,number,new Date(),new Date(),openId);
                userMapper.insert(user);
                userDetails = userDetailsService.loadUserByUsername(openId);
            }

            // 比较用户密码是否匹配
            if(!number.equals(userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid password.");
            }
            System.out.println("认证通过");
            // 认证通过，返回UsernamePasswordAuthenticationToken对象
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }




    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}