package com.takeout.backend.controller.seller.category;


import com.takeout.backend.pojo.Sellercategory;
import com.takeout.backend.service.seller.category.InfoSellerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InfoSellerCategoryController {
    @Autowired
    private InfoSellerCategoryService infoSellerCategoryService;

    @GetMapping("/seller/category/infosellercategory/")
    public List<Sellercategory> infosellercategory() {
        return infoSellerCategoryService.infoSellerCategory();
    }
}
