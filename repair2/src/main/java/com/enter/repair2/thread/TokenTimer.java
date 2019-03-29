package com.enter.repair2.thread;

import com.enter.repair2.config.RepairAppConfig;
import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.utils.WeChatAccessTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Liquid
 * @类名： TokenTimer
 * @描述：
 * @date 2019/3/28
 */
@Slf4j
public class TokenTimer {
    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    public static void start() {
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    RepairAppConfig.accessToken = WeChatAccessTokenUtil.getAccessToken(RepairAppConfig.APP_ID, RepairAppConfig.SECRET);
                    RepairAppConfig.enterpriseWeChatAccessToken = WeChatAccessTokenUtil.getEnterpriseWeChatAccessToken(RepairAppConfig.ENTERPRISE_WE_CHAT_APP_ID,
                            RepairAppConfig.ENTERPRISE_WE_CHAT_SECRET);
                    log.info("获取access_token成功，有效时长{}秒 token:{}", RepairAppConfig.ACCESS_TOKEN_EFFECTIVE_SECOND, RepairAppConfig.enterpriseWeChatAccessToken);
                } catch (CheckedException e) {
                    log.error("定时器出异常了！{} 重新获取", e);
                    start();
                }

            }
        }, 0, RepairAppConfig.ACCESS_TOKEN_EFFECTIVE_SECOND / 3600, TimeUnit.HOURS);
    }

}
