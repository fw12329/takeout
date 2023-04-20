package com.takeout.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.UserMapper;
import com.takeout.backend.pojo.User;
import com.takeout.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> register(String number) {
        Map<String,String> map = new HashMap<>();
        String username = "QS";
        User user = new User(null,username,number,new Date(),new Date());
        userMapper.insert(user);
        map.put("error_message","success");
        try{

        } catch (Exception e) {
            map.put("error_message","错误");
        }
        return map;
    }
}
