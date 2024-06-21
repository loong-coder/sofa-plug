package com.pimcenter.base.service;

import com.pimcenter.base.service.dto.CityDTO;
import com.pimcenter.base.service.fallback.CityServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @ClassName CityService
 * @Description 获取城市信息的Feign客户端
 * @Author yuanting.mao
 * @Date 2024/6/21 10:17
 * @Version 1.0
 */
@FeignClient(contextId = "cityService", value = "biz-base", fallback = CityServiceFallback.class)
public interface CityService {

    @GetMapping("findAllCities")
    List<CityDTO> findAllCities();
}
