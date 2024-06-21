package com.pimcenter.schedule.rest;

import com.github.javafaker.Faker;

import com.pimcenter.schedule.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MongoController
 * @Description 集成测试mongoDB 整合
 * @Author yuanting.mao
 * @Date 2024/6/20 18:46
 * @Version 1.0
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/testSave")
    public Boolean testSave() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().name());
        user.setAge(faker.number().numberBetween(1, 100));
        user.setIc(faker.idNumber().valid());
        user.setCreatedDate(faker.date().birthday());

        mongoTemplate.save(user);
        return true;
    }


    @GetMapping("/getAll")
    public List<User> getAll() {
        return mongoTemplate.findAll(User.class);
    }
}
