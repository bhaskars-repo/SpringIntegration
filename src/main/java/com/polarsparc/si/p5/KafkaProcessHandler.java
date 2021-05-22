/*
 * Name:   KafkaProcessHandler
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class KafkaProcessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProcessHandler.class);

    public void handler(Message<?> msg) {
        LOGGER.info("Kafka message payload (in Xml): {}", msg.getPayload().toString());
    }
}
