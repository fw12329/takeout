package com.takeout.backend.controller.user.account;


import com.takeout.backend.service.user.account.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/user/account/token/")
    public Map<String,String> getToken(@RequestParam @Parameter(description = "appid <br>secret<br>code:(wx.login获取code)",name = "说明") Map<String,String> data) {
        return loginService.getToken(data);
    }

}
