package com.takeout.backend.service.user.seller;

import com.takeout.backend.pojo.Seller;

import java.util.List;

public interface GetSellerListService {
    List<Seller> getSellerlist(Integer page);
}
