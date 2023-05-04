package com.takeout.backend.service.user.order;

import com.takeout.backend.pojo.Details;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface GetOrderDetailsService {
    ArrayList<Map<String,Object>> getorderdetails(Map<String,String> map);
}
