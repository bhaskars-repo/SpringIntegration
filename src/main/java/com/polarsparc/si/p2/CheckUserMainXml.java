/*
 * Name:   CheckUserMainXml
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CheckUserMainXml {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserMainXml.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("p2/CheckUser.xml");

        CheckUserGateway gateway = (CheckUserGateway) context.getBean("gateway");

        LOGGER.info("CheckUser for alice: {} with Xml", Boolean.toString(gateway.checkUser("alice")));

        LOGGER.info("CheckUser for john: {} with Xml", Boolean.toString(gateway.checkUser("john")));
    }
}
