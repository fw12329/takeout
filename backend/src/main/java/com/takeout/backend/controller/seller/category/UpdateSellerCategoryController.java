package com.takeout.backend.controller.seller.category;


import com.takeout.backend.service.seller.category.UpdateSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController

public class UpdateSellerCategoryController {
    @Autowired
    private UpdateSellerCategoryService updateSellerCategoryService;

    @PostMapping("/seller/category/updatesellercategory/")
    public Map<String,String> updateSellerCategory(@RequestBody Map<String,String> data) {
        Integer category_id = Integer.parseInt(data.get("category_id"));
        String category_name = data.get("category_name");
        return updateSellerCategoryService.updateSellerCategory(category_id,category_name);
    }
}
