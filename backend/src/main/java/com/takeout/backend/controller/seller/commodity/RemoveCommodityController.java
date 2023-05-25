package com.takeout.backend.controller.seller.commodity;


import com.takeout.backend.service.seller.commodity.RemoveCommodityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveCommodityController {

    private RemoveCommodityService removeCommodityService;

    @PostMapping("/seller/commodity/removecommodity/")
    public Map<String,String> removeCommodity(@RequestBody Map<String,String> data) {
        Integer product_id = Integer.parseInt(data.get("product_id"));
        return removeCommodityService.RemoveCommodity(product_id);
    }
}
