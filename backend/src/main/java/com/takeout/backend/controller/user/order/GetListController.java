package com.takeout.backend.controller.user.order;

import com.takeout.backend.pojo.Orders;
import com.takeout.backend.service.user.order.GetListService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetListController {
    @Autowired
    private GetListService getListService;


    @PostMapping("/user/order/getlist/")
    public List<Map<String,Object>> getList(@RequestBody Map<String,String> data) {
        Integer page = Integer.parseInt(data.get("page"));
        return getListService.getList(page);
    }
}
