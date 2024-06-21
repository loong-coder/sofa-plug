package com.pimcenter.base.rest;

import com.alibaba.fastjson.JSONObject;
import com.pimcenter.base.entity.City;
import com.pimcenter.base.entity.CityVo;
import com.pimcenter.base.repository.CityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisController
 * @Description TODO
 * @Author yuanting.mao
 * @Date 2024/6/20 16:14
 * @Version 1.0
 */
@RestController
@Tag(name = "Redis 测试")
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/redis/findAll")
    @Operation(summary = "从Redis中获取城市列表", method = "GET")
    public CityVo findAll() {

        CityVo cityVo = new CityVo();
        if (redisTemplate.hasKey("city")) {
            List<City> city = JSONObject.parseArray(redisTemplate.opsForValue().get("city"), City.class);
            cityVo.setCityList(city);
            cityVo.setSource("redis");
            return cityVo;
        }
        List<City> all = cityRepository.findAll();
        cityVo.setCityList(all);
        cityVo.setSource("db");
        redisTemplate.opsForValue().set("city", JSONObject.toJSONString(all), 10, TimeUnit.SECONDS);
        return cityVo;
    }
}
