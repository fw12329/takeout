package com.takeout.backend.controller.user;

import com.takeout.backend.service.user.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload/")
    public Map<String,String> upload(@RequestParam("file") MultipartFile file,@RequestParam("seller_id") String seller_id) {
        return uploadService.upload(file,seller_id);
    }
}
