/*
 * Name:   StrUpCase4MainConfig
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.polarsparc.si.p1.StrUpCaseGateway2;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class StrUpCase4MainConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCase4MainConfig.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StrUpCase4Config.class,
                StrUpCaseHandler4.class, StrUpCaseGateway2.class);

        StrUpCaseGateway2 gateway = (StrUpCaseGateway2) context.getBean("gateway");

        for (int i = 1; i <= 5; i++) {
            gateway.send("Spring Integration using Executor - " + i + " with Config");
        }

        for (int i = 1; i <= 5; i++) {
            String msg = gateway.receive();

            LOGGER.info("OUTPUT: {}", msg);
        }

        ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) context.getBean("executor");
        while (executor.getActiveCount() != 0) {
            try {
                Thread.sleep(1000);
            }
            catch (Exception ignored) {
            }
        }
        executor.shutdown();
    }
}
