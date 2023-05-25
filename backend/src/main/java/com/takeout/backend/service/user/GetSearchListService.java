package com.takeout.backend.service.user;

import com.takeout.backend.pojo.Seller;

import java.util.List;

public interface GetSearchListService {
    List<Seller> GetSearchList(Integer page, String keyword);
}
