package com.takeout.backend.service.impl.user.account;

import com.takeout.backend.pojo.User;
import com.takeout.backend.service.impl.utils.UserDetailsImpl;
import com.takeout.backend.service.user.account.LoginService;
import com.takeout.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Map<String, String> getToken(Map<String,String> data) {
        Map<String,String> map = new HashMap<>();
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(data.get("open_id"),null);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
            User user = loginUser.getUser();
            String jwt = JwtUtil.createJWT(user.getOpenId());
            map.put("error_message","success");
            map.put("token",jwt);
            map.put("username", user.getUsername());
        } catch (Exception e) {
            map.put("error_message",e.getMessage());
            return map;
        }



        return map;



    }
}
