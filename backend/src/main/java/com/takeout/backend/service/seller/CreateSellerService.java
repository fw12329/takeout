package com.takeout.backend.service.seller;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CreateSellerService {
    Map<String,String> create(MultipartFile photo,
                              String seller_name,
                              String seller_desc,
                              String seller_address,
                              Integer category_id,
                              String license_number);
}
