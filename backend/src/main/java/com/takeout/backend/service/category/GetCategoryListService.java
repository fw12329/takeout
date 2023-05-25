package com.takeout.backend.service.category;

import com.takeout.backend.pojo.Category;

import java.util.List;
import java.util.Map;

public interface GetCategoryListService {
    List<Category> getCategoryList(Map<String,String> data);
}
