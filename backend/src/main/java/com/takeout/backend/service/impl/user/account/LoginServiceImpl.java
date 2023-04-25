package com.takeout.backend.service.impl.user.account;

import com.takeout.backend.mapper.UserMapper;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.account.LoginService;
import com.takeout.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Map<String, String> getToken(Map<String,String> data) {
        String open_id = data.get("username");
        UserDetails userDetails = userDetailsService.loadUserByUsername(open_id);
        Map<String,String> map = new HashMap<>();
        if(userDetails == null) {


            userDetailsService.loadUserByUsername(open_id);
        }
        try {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(data.get("username"),data.get("password"));

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            System.out.println(authentication.getPrincipal());
            String openid = authentication.getPrincipal().toString();
            String jwt = JwtUtil.createJWT(openid);
            map.put("error_message","success");
            map.put("token",jwt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




        return map;



    }
}
