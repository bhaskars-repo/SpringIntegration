/*
 * Name:   CheckUserMainConfig
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CheckUserMainConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserMainConfig.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CheckUserHandler2.class,
                CheckUserErrorHandler2.class, CheckUserGateway2.class);

        CheckUserGateway2 gateway = (CheckUserGateway2) context.getBean("gateway");

        LOGGER.info("CheckUser for alice: {} with Config", Boolean.toString(gateway.checkUser("alice")));

        LOGGER.info("CheckUser for john: {} with Config", Boolean.toString(gateway.checkUser("john")));
    }
}
