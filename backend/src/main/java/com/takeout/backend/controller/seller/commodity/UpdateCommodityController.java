package com.takeout.backend.controller.seller.commodity;


import com.takeout.backend.service.seller.commodity.UpdateCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class UpdateCommodityController {

    @Autowired
    private UpdateCommodityService updateCommodityService;

    @PostMapping("/seller/commodity/updatecommodity")
    public Map<String,String> updateCommodity(@RequestParam("product_id") Integer product_id,
                                              @RequestParam("image") MultipartFile image,
                                              @RequestParam("product_name") String product_name,
                                              @RequestParam("sellercategory_id") Integer sellercategory_id,
                                              @RequestParam("description") String description,
                                              @RequestParam("status") Integer status) {
        return updateCommodityService.updateCommodity(product_id,image,product_name,sellercategory_id,description,status);

    }
}
