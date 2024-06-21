package com.pimcenter.base.service.fallback;

import com.pimcenter.base.service.CityService;
import com.pimcenter.base.service.dto.CityDTO;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName CityServiceFallback
 * @Description TODO
 * @Author yuanting.mao
 * @Date 2024/6/21 10:17
 * @Version 1.0
 */
public class CityServiceFallback implements FallbackFactory<CityService>{
    @Override
    public CityService create(Throwable cause) {
        return new CityService() {
            @Override
            public List<CityDTO> findAllCities() {
                return new ArrayList<>();
            }
        };
    }
}
