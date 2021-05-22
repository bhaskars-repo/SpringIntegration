/*
 * Name:   StrUpCaseHandler2
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
public class StrUpCaseHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrUpCaseHandler2.class);

    @ServiceActivator(inputChannel = "inChannel", outputChannel = "outChannel")
    public String handler(String msg) {
        LOGGER.info("INPUT: msg = {}", msg);

        return msg.toUpperCase();
    }
}
