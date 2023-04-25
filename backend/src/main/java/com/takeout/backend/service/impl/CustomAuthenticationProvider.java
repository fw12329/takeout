package com.takeout.backend.service.impl;

import com.takeout.backend.mapper.UserMapper;
import com.takeout.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
            String username = "QS";
            User user = new User(null,username,number,new Date(),new Date(),openId);
            userMapper.insert(user);
            userDetails = userDetailsService.loadUserByUsername(openId);
        }

        // 比较用户密码是否匹配
        if(!number.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }
        // 认证通过，返回UsernamePasswordAuthenticationToken对象
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(openId, number, userDetails.getAuthorities()));
        return new UsernamePasswordAuthenticationToken(openId, number, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
