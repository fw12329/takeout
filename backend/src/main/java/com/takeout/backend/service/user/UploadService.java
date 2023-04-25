package com.takeout.backend.service.user;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {
    Map<String,String> upload(MultipartFile file,String seller_id);
}
