package com.takeout.backend;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takeout.backend.utils.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private ObjectMapper mapper;

    @Test
    void contextLoads() throws Exception {



    }

    private String decrypt(String encryptedData, String iv, String sessionKey) throws Exception {
        byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedData);
        byte[] sessionKeyBytes = Base64.getDecoder().decode(sessionKey);
        byte[] ivBytes = Base64.getDecoder().decode(iv);

        SecretKeySpec skeySpec = new SecretKeySpec(sessionKeyBytes, "AES");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

        byte[] decryptedDataBytes = cipher.doFinal(encryptedDataBytes);
        return new String(decryptedDataBytes);
    }


}
