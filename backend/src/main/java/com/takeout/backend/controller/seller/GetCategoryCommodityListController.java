package com.takeout.backend.controller.seller;


import com.takeout.backend.service.seller.GetCategoryCommodityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetCategoryCommodityListController {
    @Autowired
    private GetCategoryCommodityListService getCategoryCommodityListService;

    @GetMapping("/seller/getcategorycommoditylist/")
    public List<Map<String,Object>> getCategoryCommodityList() {
        return getCategoryCommodityListService.GetCategoryCommodityList();
    }
}
