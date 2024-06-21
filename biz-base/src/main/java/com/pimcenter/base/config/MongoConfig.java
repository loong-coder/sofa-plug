package com.pimcenter.base.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MongoConfig
 * @Description MongoDB支持集群配置
 * @Author yuanting.mao
 * @Date 2024/3/18 13:58
 * @Version 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.mongodb")
@Data
@NoArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

    private String host;

    private String port;

    private String database;

    private String userName;

    private String password;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());

        String[] hosts = host.split(",");
        String[] ports = port.split(",");
        List<ServerAddress> addrList = new ArrayList<>();
        for (int i = 0; i < hosts.length; i++) {
            ServerAddress serverAddress = new ServerAddress(hosts[i], Integer.parseInt(ports[i]));
            addrList.add(serverAddress);
        }

        return MongoClients.create(MongoClientSettings.builder()
                .credential(credential)
                .applyToClusterSettings(settings -> settings.hosts(addrList))
                .build());
    }
}
