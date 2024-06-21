package com.pimcenter.base.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName NacosController
 * @Description 获取nacos配置信息
 * @Author yuanting.mao
 * @Date 2024/6/20 17:33
 * @Version 1.0
 */
@RestController
public class NacosController {


    @Value("${server.code}")
    private String serverCode;

    @GetMapping("/obtainNacosConfig")
    public String obtainNacosConfig() {
        return serverCode;
    }
}
