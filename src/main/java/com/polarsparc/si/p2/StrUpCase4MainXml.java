/*
 * Name:   StrUpCase4MainXml
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.polarsparc.si.p1.StrUpCaseGateway;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class StrUpCase4MainXml {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCase4MainXml.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("p2/StrUpCase4.xml");

        StrUpCaseGateway gateway = (StrUpCaseGateway) context.getBean("gateway");

        for (int i = 1; i <= 5; i++) {
            gateway.send("Spring Integration using Executor - " + i + " with Xml");
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
