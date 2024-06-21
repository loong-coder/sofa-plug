package com.pimcenter.schedule.rest;

import com.alibaba.fastjson.JSONObject;

import com.pimcenter.schedule.entity.City;
import com.pimcenter.schedule.entity.CityVo;
import com.pimcenter.schedule.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class RedisController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping("/redis/findAll")
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
