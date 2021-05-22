/*
 * Name:   StrUpCaseMainConfig
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

public class StrUpCaseMainConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCaseMainConfig.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(StrUpCaseConfig.class,
                StrUpCaseHandler2.class);

        Message<?> in = MessageBuilder.withPayload("Hello Spring Integration with Config").build();

        MessageChannel inMsg = (MessageChannel) context.getBean("inChannel");

        PollableChannel outMsg = (PollableChannel) context.getBean("outChannel");

        inMsg.send(in);

        Message<?> out = outMsg.receive();

        LOGGER.info("OUTPUT: {}", out);
    }
}
