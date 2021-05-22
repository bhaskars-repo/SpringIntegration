/*
 * Name:   CarServiceSplitter
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p8;

import com.polarsparc.si.p6.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;

public class CarServiceSplitter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceSplitter.class);

    public List<Message<Car>> splitter(Message<Car> input) {
        List<Message<Car>> tasks = new ArrayList<>();
        Car p1 = Car.makeClone(input.getPayload());
        Message<Car> inspection = MessageBuilder.withPayload(p1)
                .setHeader("SERVICE_TYPE", "INSP")
                .build();
        tasks.add(inspection);

        LOGGER.info("Inspection message: {} (in Xml)", input.toString());

        Car p2 = Car.makeClone(input.getPayload());
        Message<Car> recommended = MessageBuilder.withPayload(p2)
                .setHeader("SERVICE_TYPE", "RECO")
                .build();
        tasks.add(recommended);

        LOGGER.info("Recommended message: {} (in Xml)", input.toString());

        return tasks;
    }
}
