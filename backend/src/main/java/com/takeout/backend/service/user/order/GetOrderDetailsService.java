package com.takeout.backend.service.user.order;

import com.takeout.backend.pojo.Details;

import java.util.List;
import java.util.Map;

public interface GetOrderDetailsService {
    List<Details> getorderdetails(Map<String,String> map);
}
