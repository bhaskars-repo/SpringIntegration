/*
 * Name:   RefNoHandler2
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;

@Configuration
@EnableIntegration
public class RefNoHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefNoHandler2.class);

    private static int nextNo = 1;

    private static String REF_NO_FMT = "%s-%05d";

    @ServiceActivator
    public Message<Car> assignRefNo(Message<Car> input) {
        input.getPayload().setRef(String.format(REF_NO_FMT, input.getPayload().getMake().substring(0, 1), nextNo++));

        LOGGER.info("Input: {} (in Config)", input.toString());

        return input;
    }
}
