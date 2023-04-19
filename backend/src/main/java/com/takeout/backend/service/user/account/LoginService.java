package com.takeout.backend.service.user.account;

import java.util.Map;

public interface LoginService {
    Map<String,String> login(String username);
}
