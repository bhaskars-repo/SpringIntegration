/*
 * Name:   AmqpPublisher
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AmqpPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpPublisher.class);

    private static final String MESSAGE_TEXT = "AMQP with Spring Integration - %d";

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("p5/AmqpPublisher.xml");

        AmqpTemplate template = (AmqpTemplate) context.getBean("amqpTemplate");

        for (int i = 1; i <= 10; i++) {
            String text = String.format(MESSAGE_TEXT, i);
            try {
                template.convertAndSend("si_exchange", "si_queue", text);

                LOGGER.info("Published message - {}", text);
            }
            catch (AmqpException ex) {
                LOGGER.error(ex.getLocalizedMessage());
            }
        }
    }
}
