package com.takeout.backend.controller.seller;


import com.takeout.backend.pojo.Category;
import com.takeout.backend.service.seller.CategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerCategoryInfoController {

    @Autowired
    private CategoryInfoService categoryInfoService;

    @GetMapping("/seller/sellercategoryinfo/")
    public List<Category> getlist() {
        return categoryInfoService.getList();
    }
}
