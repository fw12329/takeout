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
    public Map<String,String> crate(@RequestParam(value = "image", required = false) MultipartFile photo,
                                    @RequestParam(value = "seller_name", required = false) String seller_name,
                                    @RequestParam(value = "seller_desc", required = false) String seller_desc,
                                    @RequestParam(value = "seller_address", required = false) String seller_address,
                                    @RequestParam(value = "category_id", required = false) Integer category_id,
                                    @RequestParam(value = "license_number", required = false) String license_number) {
        return createSellerService.create(photo,seller_name,seller_desc,seller_address,category_id,license_number);
    }


}
