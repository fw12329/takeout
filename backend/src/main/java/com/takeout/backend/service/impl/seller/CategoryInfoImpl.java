package com.takeout.backend.service.impl.seller;


import com.takeout.backend.mapper.CategoryMapper;
import com.takeout.backend.mapper.SellerCategoryMapper;
import com.takeout.backend.pojo.Category;
import com.takeout.backend.service.seller.CategoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryInfoImpl implements CategoryInfoService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getList() {
        return categoryMapper.selectList(null);
    }
}
