package com.pimcenter.schedule.rest;

import com.alibaba.fastjson.JSONObject;
import com.github.javafaker.Faker;
import com.pimcenter.schedule.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName KafkaController
 * @Description 测试kafka 发送消息
 * @Author yuanting.mao
 * @Date 2024/6/22 15:49
 * @Version 1.0
 */
@RestController
public class KafkaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("sendMessage")
    public Boolean sendMessage() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().name());
        user.setAge(faker.number().numberBetween(1, 100));
        user.setIc(faker.idNumber().valid());
        user.setCreatedDate(faker.date().birthday());
        kafkaTemplate.send("user_topic", JSONObject.toJSONString(user)).exceptionally(e -> {
            LOGGER.error("kafka send message error", e);
            return null;
        });
        return true;
    }
}
