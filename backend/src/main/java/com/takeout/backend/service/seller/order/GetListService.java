package com.takeout.backend.service.seller.order;

import com.takeout.backend.pojo.Order;

import java.util.List;
import java.util.Map;

public interface GetListService {
    List<Order> getList(Map<String,String> data);
}