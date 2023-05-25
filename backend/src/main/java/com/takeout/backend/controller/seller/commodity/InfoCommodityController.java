package com.takeout.backend.controller.seller.commodity;


import com.takeout.backend.service.seller.commodity.InfoCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InfoCommodityController {

    @Autowired
    private InfoCommodityService infoCommodityService;

    @PostMapping("/seller/commodity/infoCommodity/")
    public Map<String, Object> infoCommodity(@RequestBody Map<String,String> data) {
        return infoCommodityService.infoCommodity(data);
    }
}
