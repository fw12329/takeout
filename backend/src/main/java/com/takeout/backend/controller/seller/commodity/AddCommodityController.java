package com.takeout.backend.controller.seller.commodity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.takeout.backend.service.seller.commodity.AddCommodityService;
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


    @PostMapping("/seller/commodity/addcommodity/")
    public Map<String,String> addCommodity(@RequestParam("image")MultipartFile image,
                                           @RequestParam("product_name") String product_name,
                                           @RequestParam("status") Integer status,
                                           @RequestParam("desc") String desc,
                                           @RequestParam("specs") String specs,
                                           @RequestParam("category") String category) throws JsonProcessingException {

        return addCommodityService.addCommodity(image,product_name,status,desc,specs,category);
}


}
