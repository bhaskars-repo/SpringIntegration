/*
 * Name:   StrUpCase2MainXml
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StrUpCase2MainXml {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCase2MainXml.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("p1/StrUpCase2.xml");

        StrUpCaseGateway gateway = (StrUpCaseGateway) context.getBean("gateway");
        gateway.send("Spring Integration using Gateway with Xml");

        String msg = gateway.receive();

        LOGGER.info("OUTPUT: {}", msg);
    }
}
