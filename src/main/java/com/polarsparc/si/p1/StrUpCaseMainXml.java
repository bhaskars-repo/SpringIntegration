/*
 * Name:   StrUpCaseMainXml
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.integration.support.MessageBuilder;

public class StrUpCaseMainXml {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCaseMainXml.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("p1/StrUpCase.xml");

        Message<?> in = MessageBuilder.withPayload("Hello Spring Integration with Xml").build();

        MessageChannel inMsg = (MessageChannel) context.getBean("inChannel");

        PollableChannel outMsg = (PollableChannel) context.getBean("outChannel");

        inMsg.send(in);

        Message<?> out = outMsg.receive();

        LOGGER.info("OUTPUT: {}", out);
    }
}
