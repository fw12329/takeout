package com.takeout.backend.controller.seller;

import com.takeout.backend.mapper.SellerCategoryMapper;
import com.takeout.backend.service.seller.CreateSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CreateSellerController {

    @Autowired
    private CreateSellerService createSellerService;

    @PostMapping("/seller/createseller/")
    public Map<String,String> crate(@RequestParam("image") MultipartFile photo,
                                    @RequestParam("seller_name") String seller_name,
                                    @RequestParam("seller-desc") String seller_desc,
                                    @RequestParam("seller_address") String seller_address,
                                    @RequestParam("category_id") Integer category_id,
                                    @RequestParam("license_number") String license_number) {
        return createSellerService.create(photo,seller_name,seller_desc,seller_address,category_id,license_number);
    }


}
