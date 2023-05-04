package com.takeout.backend.controller.user.order;


import com.takeout.backend.pojo.Details;
import com.takeout.backend.service.user.order.GetOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderDetailsController {
    @Autowired
    private GetOrderDetailsService getOrderDetailsService;

    @PostMapping("/user/order/getorderdetails/")
    public ArrayList<Map<String,Object>> getOrderDetails(@RequestBody Map<String,String> data) {
        return getOrderDetailsService.getorderdetails(data);
    }
}
