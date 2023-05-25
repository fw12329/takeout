package com.takeout.backend.controller.seller.order;


import com.takeout.backend.pojo.Orders;
import com.takeout.backend.service.seller.order.GetOrdersListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetOrdersListController {

    @Autowired
    private GetOrdersListService getOrdersListService;

    @PostMapping("/seller/order/")
    public List<Orders> getOrdersList(@RequestBody Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        return getOrdersListService.getOrders(page);
    }
}
