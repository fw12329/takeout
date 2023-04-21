package com.takeout.backend.controller.seller.order;

import com.takeout.backend.pojo.Order;
import com.takeout.backend.service.seller.order.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetListController {
    @Autowired
    private GetListService getListService;

    @PostMapping("")
    public List<Order> getList(@RequestParam Map<String,String> data) {
        return getListService.getList(data);
    }
}
