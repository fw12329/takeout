package com.xxx.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * swagger配置文件
 * @author admin
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    Docket api() {
        // springfox 的基本API配置
        return new Docket(DocumentationType.OAS_30)
                // 将映射的路径当作base Path，添加给apis
                .pathMapping("/")
                // 设置api 文档基本信息： 常见内容：版本，主题，联系人...
                .apiInfo(null)
                // 分类，可根据groupName选择不同的api 列表
                .groupName("public api")
                // 设置host信息
                .host("http://192.168.0.1:8080")
                // 支持的协议
                .protocols(new HashSet<>(Arrays.asList("http", "https")))
                .enable(true)
                // 创建API 选择器
                .select()
                // 暴露的给Swagger的API，指定扫描包的接口
                .apis(RequestHandlerSelectors.basePackage("com.xxx"))
                // 符合要求的接口
                .paths(PathSelectors.ant("/user/**"))
                .build();
    }
}
