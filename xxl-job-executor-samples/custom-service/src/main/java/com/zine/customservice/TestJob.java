package com.zine.customservice;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zine
 * @description:
 * @date 2024/02/02 17:18
 */
@Slf4j
@Component
public class TestJob {

    @XxlJob("customJobHandler")
    public void customJobHandler() throws Exception {
        log.info("==== custom job handler start ====");
        for (int i = 0; i < 5; i++) {
            log.info("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        log.info("==== custom job handler end ====");
    }
}
