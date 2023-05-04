package com.takeout.backend.service.seller.commodity;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface AddCommodityService {
    Map<String,String> addCommodity(MultipartFile image,
                                    String product_name,
                                    String desc,
                                    String specs) throws JsonProcessingException;
}
