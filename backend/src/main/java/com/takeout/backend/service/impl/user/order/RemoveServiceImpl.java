package com.takeout.backend.service.impl.user.order;

import com.takeout.backend.service.user.order.RemoveService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        Map<String,String> map = new HashMap<>();
        Integer product_id = Integer.parseInt(data.get("product_id"));
        Integer seller_id = Integer.parseInt(data.get("seller_id"));
        
        return null;
    }
}
