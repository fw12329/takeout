package com.takeout.backend.controller.user.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RemoveController {


    @PostMapping("/user/order/remove/")
    public Map<String,String> remove(@RequestParam Map<String,String> data) {
        Map<String,String> map = new HashMap<>();
        map.put("abc","abc");
        return map;
    }
}
