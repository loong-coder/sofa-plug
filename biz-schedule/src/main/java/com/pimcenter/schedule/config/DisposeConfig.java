//package com.pimcenter.schedule.config;
//
//
//import com.mongodb.client.MongoClient;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName DisposeConfig
// * @Description 销毁资源
// * @Author yuanting.mao
// * @Date 2024/6/25 17:23
// * @Version 1.0
// */
//@Component
//public class DisposeConfig implements DisposableBean {
//
//    @Autowired
//    private MongoClient mongoClient;
//
//    @Override
//    public void destroy() throws Exception {
//        mongoClient.close();
//    }
//}
