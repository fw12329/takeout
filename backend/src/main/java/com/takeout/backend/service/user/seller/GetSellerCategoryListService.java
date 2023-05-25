package com.takeout.backend.service.user.seller;

import com.takeout.backend.pojo.Seller;

import java.util.List;
import java.util.Map;

public interface GetSellerCategoryListService {
    List<Map<String,Object>> getSellerCategoryList(Integer seller_id);
}
