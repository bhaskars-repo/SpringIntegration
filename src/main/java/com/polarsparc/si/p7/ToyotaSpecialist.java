/*
 * Name:   ToyotaSpecialist
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p7;

import com.polarsparc.si.p6.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class ToyotaSpecialist {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToyotaSpecialist.class);

    private static String WHEEL_BALANCE = "WHEEL_BALANCE";
    private static String LABOR_CHARGES = "LABOR_CHARGES";

    public Message<?> completeService(Message<Car> input) {
        if (input.getPayload().getServices().containsKey(WHEEL_BALANCE)) {
            input.getPayload().getServices().put(LABOR_CHARGES, (float) 99.95);
        }
        else {
            input.getPayload().getServices().put(LABOR_CHARGES, (float) 39.95);
        }

        LOGGER.info("Input: {} (in Xml)", input.toString());

        return input;
    }
}
