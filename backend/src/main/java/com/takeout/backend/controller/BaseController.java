package com.takeout.backend.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @ExceptionHandler(ServerException.class)
    public Map<String,String> handleException(Throwable e) {
        Map<String,String> map = new HashMap<>();
        map.put("error_message",e.getMessage());
        return map;
    }
}
