package com.takeout.backend.service.user.order;

import java.util.List;
import java.util.Map;

public interface CommitOrderService {
    Map<String,String> commitOrder(Map<String,Object> data);
}
