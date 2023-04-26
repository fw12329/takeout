package com.takeout.backend.service.impl;

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

import java.util.Date;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String openId = authentication.getName();
        String number = authentication.getCredentials().toString();

        UserDetails userDetails = userDetailsService.loadUserByUsername(openId);
        if(userDetails == null) {
            String username = "QS" + RandomStringUtils.randomNumeric(8);
            System.out.println(username);
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
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}