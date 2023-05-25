package com.takeout.backend.service.impl.category;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.takeout.backend.mapper.CategoryMapper;
import com.takeout.backend.pojo.Category;
import com.takeout.backend.service.category.GetCategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GetCategoryListServiceImpl implements GetCategoryListService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryList(Map<String, String> data) {
        Integer category_id = Integer.parseInt(data.get("category_id"));

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",category_id);



        List<Category> categories = categoryMapper.selectList(queryWrapper);
        return categories;
    }
}
