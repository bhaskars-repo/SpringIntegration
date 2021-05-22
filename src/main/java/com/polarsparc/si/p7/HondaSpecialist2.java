/*
 * Name:   HondaSpecialist2
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p7;

import com.polarsparc.si.p6.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;

@Configuration
@EnableIntegration
public class HondaSpecialist2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(HondaSpecialist2.class);

    private static String WHEEL_BALANCE = "WHEEL_BALANCE";
    private static String LABOR_CHARGES = "LABOR_CHARGES";

    @ServiceActivator(inputChannel = "hondaChannel", outputChannel = "xformChannel")
    public Message<?> completeService(Message<Car> input) {
        if (input.getPayload().getServices().containsKey(WHEEL_BALANCE)) {
            input.getPayload().getServices().put(LABOR_CHARGES, (float) 89.95);
        }
        else {
            input.getPayload().getServices().put(LABOR_CHARGES, (float) 29.95);
        }

        LOGGER.info("Input: {}  (in Config)", input.toString());

        return input;
    }
}
