/*
 * Name:   CarServiceHandler
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class CarServiceHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceHandler.class);

    private static String OIL_CHANGE = "OIL_CHANGE";
    private static String AIR_FILTER = "AIR_FILTER";
    private static String WHEEL_BALANCE = "WHEEL_BALANCE";

    public Message<?> recommendedServices(Message<Car> input) {
        input.getPayload().getServices().put(OIL_CHANGE, (float) 29.95);
        input.getPayload().getServices().put(AIR_FILTER, (float) 19.95);

        if (input.getPayload().getMiles() % 15000 == 0) {
            input.getPayload().getServices().put(WHEEL_BALANCE, (float) 69.95);
        }

        LOGGER.info("Input: {} (in Xml)", input.toString());

        return input;
    }
}
