package com.takeout.backend.controller.user.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.takeout.backend.service.user.order.CommitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommitOrderController {
    @Autowired
    private CommitOrderService commitOrderService;

    @PostMapping("/user/order/commit/")
    public Map<String,String> commitOrders(@RequestBody Map<String,String> data) throws JsonProcessingException {


        Integer seller_id = Integer.parseInt(data.get("seller_id"));
        String contact_name = data.get("contact_name");
        String contact_phone = data.get("contact_phone");
        String contact_address = data.get("contact_address");
        String is_play = data.get("is_play");
        Integer status = Integer.parseInt(data.get("status"));
        String orders = data.get("orders");

        return commitOrderService.commitOrder(seller_id,contact_name,contact_phone,contact_address,is_play,status,orders);
    }
}
