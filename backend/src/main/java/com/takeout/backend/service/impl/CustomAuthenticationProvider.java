package com.takeout.backend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.backend.mapper.UserMapper;
import com.takeout.backend.pojo.User;
import com.takeout.backend.utils.HttpClientUtil;
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
import java.util.HashMap;
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
        BufferedReader reader;
        String line;
        Map<Object,Object> map;
        String openId;
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        try {

            reader = request.getReader();
            StringBuilder stringBuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) {

                stringBuilder.append(line);
            }
            String requestBody = stringBuilder.toString();
            System.out.println(requestBody);
            map = mapper.readValue(requestBody, Map.class);
            String code = map.get("code").toString();
            String appid = map.get("appid").toString();
            String secret = map.get("secret").toString();
            Map<String,String> result = new HashMap<>();
            result.put("appid",appid);
            result.put("secret",secret);
            result.put("js_code",code);
            result.put("grant_type", "authorization_code");
            String s = HttpClientUtil.doGet(url,result);
            Map<String,String> WxUserInfo = mapper.readValue(s, Map.class);

            if(WxUserInfo.get("session_key") == null) {
                throw new BadCredentialsException("code失效");
            }
            openId = WxUserInfo.get("openid");
            UserDetails userDetails = userDetailsService.loadUserByUsername(openId);
            if(userDetails == null) {
                String username = "QS" + RandomStringUtils.randomNumeric(8);
                User user = new User(null,username,"1145141919810",new Date(),new Date(),openId);
                userMapper.insert(user);
                userDetails = userDetailsService.loadUserByUsername(openId);
            }
            //userDetails.getUsername返回的是open_id比较open_id是否相等
            if(!openId.equals(userDetails.getUsername())) {
                throw new BadCredentialsException("Invalid openid.");
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