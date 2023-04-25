package com.takeout.backend.service.impl.user;

import com.takeout.backend.service.user.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {


    @Override
    public Map<String, String> upload(MultipartFile file,String seller_id) {
        if(file.isEmpty()) {
            Map<String,String> map = new HashMap<>();
            map.put("error_message","图片上传失败");
            return map;
        }
        String originalFilename = file.getOriginalFilename();
        String ext = "." + originalFilename.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-","");
        String fileName = uuid + ext;
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String pre = applicationHome.getDir().getParentFile().getParentFile().getAbsoluteFile() + "\\src\\main\\images";
        String path = pre + fileName;
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
