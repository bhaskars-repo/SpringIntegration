/*
 * Name:   StrUpCase2MainConfig
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StrUpCase2MainConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCase2MainConfig.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StrUpCaseConfig.class,
                StrUpCaseHandler2.class, StrUpCaseGateway2.class);

        StrUpCaseGateway2 gateway = (StrUpCaseGateway2) context.getBean("gateway");
        gateway.send("Spring Integration using Gateway with Config");

        String msg = gateway.receive();

        LOGGER.info("OUTPUT: {}", msg);
    }
}
