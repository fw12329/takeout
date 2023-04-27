package com.takeout.backend.controller.user.order;

import com.takeout.backend.service.user.order.CommitOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CommitOrderController {
    @Autowired
    private CommitOrderService commitOrderService;

    @PostMapping("/user/order/commit/")
    public Map<String,String> add(@RequestBody Map<String,Object> data) {

        return commitOrderService.commitOrder(data);
    }
}
