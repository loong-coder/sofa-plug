package com.pimcenter.schedule.release;

import ch.qos.logback.classic.selector.ContextSelector;
import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import com.alipay.sofa.ark.spi.event.biz.BeforeBizRecycleEvent;
import com.alipay.sofa.ark.spi.service.event.EventHandler;
import com.alipay.sofa.koupleless.adapter.ArkLogbackContextSelector;
import org.springframework.stereotype.Component;


/**
 * @ClassName BizRecyclePostHook
 * @Description ArkLogbackContextSelector移除对应 ClassLoader 的 Context
 * @Author yuanting.mao
 * @Date 2024/6/13 16:10
 * @Version 1.0
 */
@Component
public class ScheduleBizRecyclePostHook implements EventHandler<BeforeBizRecycleEvent> {
    @Override
    public void handleEvent(BeforeBizRecycleEvent event) {
        try {
            ContextSelector contextSelector = ContextSelectorStaticBinder.getSingleton().getContextSelector();
            if (contextSelector instanceof ArkLogbackContextSelector) {
                ClassLoader bizClassLoader = event.getSource().getBizClassLoader();
                ((ArkLogbackContextSelector)contextSelector).removeContext(bizClassLoader);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
