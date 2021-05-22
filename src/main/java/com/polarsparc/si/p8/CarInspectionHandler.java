/*
 * Name:   CarInspectionHandler
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p8;

import com.polarsparc.si.p6.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

import java.util.Random;

public class CarInspectionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarInspectionHandler.class);

    private static String WIPERS = "WIPERS";
    private static String HEADLIGHT_LAMP = "HEADLIGHT_LAMP";
    private static String BRAKE_PADS = "BRAKE_PADS";

    private static Random rand = new Random();

    public Message<?> inspectionServices(Message<Car> input) {
        switch (rand.nextInt(3)) {
            case 0:
                input.getPayload().getServices().put(WIPERS, (float) 21.95);
                break;
            case 1:
                input.getPayload().getServices().put(HEADLIGHT_LAMP, (float) 37.95);
                break;
            case 2:
                input.getPayload().getServices().put(BRAKE_PADS, (float) 44.95);
                break;
        }

        LOGGER.info("Input: {} (in Xml)", input.toString());

        return input;
    }
}
