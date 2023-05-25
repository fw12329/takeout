package com.takeout.backend.service.user.order;

import com.takeout.backend.pojo.Orders;

import java.util.List;
import java.util.Map;

public interface GetListService {
    List<Map<String,Object>> getList(Integer page);
}
