package com.takeout.backend.service.seller.commodity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UpdateCommodityService {
    Map<String,String> updateCommodity(Integer product_id,
                                       MultipartFile image,
                                       String product_name,
                                       Integer sellercategory_id,
                                       String description,
                                       String specs,
                                       Integer status,
                                       String category) throws Exception;
}
