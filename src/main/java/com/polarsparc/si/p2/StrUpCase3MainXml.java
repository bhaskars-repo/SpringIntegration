/*
 * Name:   StrUpCase3MainXml
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

public class StrUpCase3MainXml {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCase3MainXml.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("p2/StrUpCase3.xml");

        StrUpCaseGateway gateway = (StrUpCaseGateway) context.getBean("gateway");
        gateway.send("Spring Integration using WireTap with Xml");

        String msg = gateway.receive();

        LOGGER.info("OUTPUT: {}", msg);
    }
}
