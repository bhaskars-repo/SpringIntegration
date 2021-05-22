/*
 * Name:   KafkaProcessHandler2
 * Author: Bhaskar S
 * Date:   05/15/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;

@Configuration
@EnableIntegration
public class KafkaProcessHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProcessHandler2.class);

    @ServiceActivator(inputChannel = "inKafkaChannel")
    public void handler(Message<?> msg) {
        LOGGER.info("Kafka message payload (in Config): {}", msg.getPayload().toString());
    }
}
