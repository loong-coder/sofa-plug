package com.pimcenter.base.rest;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.pimcenter.base.entity.City;
import com.pimcenter.base.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName EsController
 * @Description 测下ES的连接
 * @Author yuanting.mao
 * @Date 2024/6/25 10:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/es")
public class EsController {

    @Value("${spring.elasticsearch.prefix}")
    private String prefix;

    private String index = "base_biz_city";

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/saveCity")
    public Boolean saveCity() throws IOException {
        List<City> cities = cityRepository.findAll();
        BulkRequest.Builder builder = new BulkRequest.Builder();
        for (City city : cities) {
            builder.operations(operationBuilder -> operationBuilder
                    .index(indexBuilder -> indexBuilder.index(getIndex())
                            .id(String.valueOf(city.getCityId()))
                            .document(city)
                    )
            );
        }
        elasticsearchClient.bulk(builder.build());
        return true;
    }

    @GetMapping("/queryCity")
    public List<City> queryCity() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(builder -> builder
                .index(getIndex()));
        SearchResponse<City> search = elasticsearchClient.search(searchRequest, City.class);
        return search.hits().hits().stream().map(Hit::source).toList();
    }


    private String getIndex() {
        return prefix + "_" + index;
    }
}
