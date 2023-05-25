package com.takeout.backend.controller.category;


import com.takeout.backend.pojo.Category;
import com.takeout.backend.service.category.GetCategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetCategoryListController {

    @Autowired
    private GetCategoryListService getCategoryListService;

    @PostMapping("/category/getcategorylist/")
    public List<Category> getcategorylist(@RequestBody Map<String,String> data){
        return getCategoryListService.getCategoryList(data);
    }
}
