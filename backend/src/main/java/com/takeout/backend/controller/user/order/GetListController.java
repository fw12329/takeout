package com.takeout.backend.controller.user.order;

import com.takeout.backend.pojo.Orders;
import com.takeout.backend.service.user.order.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetListController {
    @Autowired
    private GetListService getListService;


    @GetMapping("/user/order/getlist/")
    public List<Orders> getList() {
        return getListService.getList();
    }
}
