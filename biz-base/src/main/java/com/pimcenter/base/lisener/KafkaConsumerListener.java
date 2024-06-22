package com.pimcenter.base.lisener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @ClassName KafakListener
 * @Description TODO
 * @Author yuanting.mao
 * @Date 2024/6/22 16:08
 * @Version 1.0
 */
@Component
public class KafkaConsumerListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @KafkaListener(topics = "user_topic", groupId = "base-group")
    public void consumer(String message, Acknowledgment ack) {
        LOGGER.info("Received message: {}" , message);
        ack.acknowledge();
    }
}
