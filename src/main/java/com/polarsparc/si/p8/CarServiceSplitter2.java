/*
 * Name:   CarServiceSplitter2
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p8;

import com.polarsparc.si.p6.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableIntegration
public class CarServiceSplitter2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceSplitter2.class);

    @Splitter(inputChannel = "splitChannel", outputChannel = "routerChannel")
    public List<Message<Car>> splitter(Message<Car> input) {
        List<Message<Car>> tasks = new ArrayList<>();
        Car p1 = Car.makeClone(input.getPayload());
        Message<Car> inspection = MessageBuilder.withPayload(p1)
                .setHeader("SERVICE_TYPE", "INSP")
                .build();
        tasks.add(inspection);

        LOGGER.info("Inspection message: {} (in Config)", input.toString());

        Car p2 = Car.makeClone(input.getPayload());
        Message<Car> recommended = MessageBuilder.withPayload(p2)
                .setHeader("SERVICE_TYPE", "RECO")
                .build();
        tasks.add(recommended);

        LOGGER.info("Recommended message: {} (in Config)", input.toString());

        return tasks;
    }
}
