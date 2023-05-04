package com.takeout.backend.controller.seller.category;

import com.takeout.backend.service.seller.category.AddSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AddSellerCategoryController {
    @Autowired
    private AddSellerCategoryService addSellerCategoryService;


    @PostMapping("/seller/category/addsellercategory/")
    public Map<String,String> addSellerCategory(@RequestBody Map<String,String> data) {
        String name = data.get("name");
        return addSellerCategoryService.addsellerCategory(name);
    }
}
