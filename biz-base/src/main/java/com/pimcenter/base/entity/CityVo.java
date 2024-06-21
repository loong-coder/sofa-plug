package com.pimcenter.base.entity;

import java.util.List;

/**
 * @ClassName CityDto
 * @Description TODO
 * @Author yuanting.mao
 * @Date 2024/6/20 16:30
 * @Version 1.0
 */
public class CityVo {

    private String source;

    private List<City> cityList;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
