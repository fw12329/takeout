package com.takeout.backend.controller.seller;

import com.takeout.backend.service.seller.AddCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class AddCommodityController {

    @Autowired
    private AddCommodityService addCommodityService;

    @PostMapping("/seller/addcommodity/")
    public Map<String,String> addCommodity(@RequestParam("image") MultipartFile image,
                                           @RequestParam("product_name") String product_name,
                                           @RequestParam("description") String description,
                                           @RequestParam("price") Double price,
                                           @RequestParam("stock") Integer stock,
                                           @RequestParam("seller_id") Integer seller_id) {

        return addCommodityService.addCommodity(image,product_name,description,price,stock,seller_id);
    }
}
