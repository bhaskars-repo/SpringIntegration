/*
 * Name:   AmqpProcessHandler2
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
public class AmqpProcessHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpProcessHandler2.class);

    @ServiceActivator(inputChannel = "amqpMsgsChannel")
    public void handler(Message<?> msg) {
        LOGGER.info("AMQP message payload (in Config): {}", msg.getPayload().toString());
    }
}
