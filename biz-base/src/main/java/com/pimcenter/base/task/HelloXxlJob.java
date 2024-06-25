package com.pimcenter.base.task;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloXxlJob
 * @Description 集成xxl-job
 * @Author yuanting.mao
 * @Date 2024/6/25 14:37
 * @Version 1.0
 */
@Component
public class HelloXxlJob {

    @Value("${spring.application.name}")
    private String applicationName;

    public static final Logger logger = LoggerFactory.getLogger(HelloXxlJob.class);

    /**
     * 简单任务
     */
    @XxlJob("sampleJobHandler")
    public void executeSampleJobHandler() {
        logger.info("XXL-JOB, :{},Hello World.", applicationName);
    }

}
