/*
 * Name:   StrUpCase3MainConfig
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
import com.polarsparc.si.p1.StrUpCaseHandler2;

public class StrUpCase3MainConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCase3MainConfig.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StrUpCase3Config.class,
                StrUpCaseHandler2.class, StrUpCaseGateway2.class);

        StrUpCaseGateway2 gateway = (StrUpCaseGateway2) context.getBean("gateway");
        gateway.send("Spring Integration using WireTap with Config");

        String msg = gateway.receive();

        LOGGER.info("OUTPUT: {}", msg);
    }
}
