package com.takeout.backend.service.user.order;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface CommitOrderService {
    Map<String,String> commitOrder(Integer seller_id,
                                   String contact_name,
                                   String contact_phone,
                                   String contact_address,
                                   String is_play,
                                   Integer status,
                                   String orders) throws JsonProcessingException;
}
