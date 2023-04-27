package com.takeout.backend.service.seller;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AddCommodityService {
    Map<String,String> addCommodity(MultipartFile imageFile,
                                    String product_name,
                                    String description,
                                    Double price,
                                    Integer stock,
                                    Integer seller_id
                                    );
}
