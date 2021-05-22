/*
 * Name:   StrUpCaseHandler4
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

@Configuration
@EnableIntegration
public class StrUpCaseHandler4 {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCaseHandler4.class);

    @ServiceActivator(inputChannel = "inChannel", outputChannel = "outChannel")
    public String handler(String msg) {
        LOGGER.info("INPUT: msg = {}", msg);

        try {
            Thread.sleep(1000);
        }
        catch (Exception ignored) {
        }

        return msg.toUpperCase();
    }
}
