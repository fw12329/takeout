package com.takeout.backend.service.seller.category;

import java.util.Map;

public interface UpdateSellerCategoryService {
    Map<String,String> updateSellerCategory(Integer id,String name);
}
