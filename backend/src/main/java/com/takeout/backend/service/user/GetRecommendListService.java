package com.takeout.backend.service.user;

import com.takeout.backend.pojo.Seller;

import java.util.List;

public interface GetRecommendListService {
    List<Seller> getRecommendList(Integer page);
}
