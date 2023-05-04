package com.takeout.backend.controller.seller.category;


import com.takeout.backend.service.seller.category.RemoveSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RemoveSellerCategoryController {

    @Autowired
    private RemoveSellerCategoryService removeSellerCategoryService;

    @PostMapping("/seller/category/removesellercategory/")
    public Map<String,String> removesellercategory(@RequestBody Map<String,Integer> data) {
        Integer id = data.get("id");
        return removeSellerCategoryService.removeSellerCategory(id);
    }
}
