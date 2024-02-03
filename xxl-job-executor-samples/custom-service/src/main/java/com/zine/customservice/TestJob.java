package com.zine.customservice;

import com.xxl.job.core.context.XxlJobHelper;
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

    @XxlJob("shardingJobHandler")
    public void shardingJobHandler() throws Exception {

        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        log.info("分片参数：当前分片序号 = {}, 总分片数 = {}", shardIndex, shardTotal);

        // 业务逻辑
        for (int i = 0; i < shardTotal; i++) {
            if (i == shardIndex) {
                log.info("第 {} 片, 命中分片开始处理", i);
            } else {
                log.info("第 {} 片, 忽略", i);
            }
        }

    }
}
