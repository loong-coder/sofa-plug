package com.pimcenter.schedule.release;

import com.alipay.sofa.ark.spi.event.biz.AfterBizStopEvent;
import com.alipay.sofa.ark.spi.service.event.EventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.apache.logging.log4j.core.selector.ContextSelector;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @ClassName BizRecyclePostHook
 * @Description TODO
 * @Author yuanting.mao
 * @Date 2024/6/13 16:10
 * @Version 1.0
 */
@Component
public class ScheduleBizRecyclePostHook implements EventHandler<AfterBizStopEvent> {
    @Override
    public void handleEvent(AfterBizStopEvent event) {
        try {
            ClassLoader bizClassLoader = event.getSource().getBizClassLoader();
            LoggerContextFactory factory = LogManager.getFactory();
            // see -> org.apache.logging.log4j.core.selector.ClassLoaderContextSelector.toContextMapKey
            String contextName = Integer.toHexString(System.identityHashCode(bizClassLoader));
            System.out.printf("biz stop:%s \n", contextName);
            ContextSelector selector = ((Log4jContextFactory) factory).getSelector();
            List<LoggerContext> loggerContexts = selector.getLoggerContexts();
            for (LoggerContext context : loggerContexts) {
                if (context.getName().equals(contextName)) {
                    boolean stop = context.stop(300, TimeUnit.MICROSECONDS);
                    System.out.printf("Stop biz %s:%s log4j2 logger context, success=%b \n", contextName, stop, stop);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
