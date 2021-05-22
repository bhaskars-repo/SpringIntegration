/*
 * Name:   CheckUserErrorHandler
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandlingException;

public class CheckUserErrorHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserErrorHandler.class);

    @ServiceActivator(inputChannel = "errChannel")
    public void handleError(Message<MessageHandlingException> msg) {
        LOGGER.error("Exception message = {}", msg);
    }
}
