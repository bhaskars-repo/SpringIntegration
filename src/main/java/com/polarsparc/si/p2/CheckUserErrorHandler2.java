/*
 * Name:   CheckUserErrorHandler2
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;

@Configuration
@EnableIntegration
public class CheckUserErrorHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserErrorHandler2.class);

    @ServiceActivator(inputChannel = "errChannel")
    public void handleError(Message<MessageHandlingException> msg) {
        LOGGER.error("Exception message = {}", msg);
    }
}
