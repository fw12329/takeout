package com.takeout.backend.service.seller.order;

import com.takeout.backend.pojo.Orders;

import java.util.List;

public interface GetOrdersListService {
    List<Orders> getOrders(Integer page);
}
