/*
 * Name:   CarServiceAggregator
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p8;

import com.polarsparc.si.p6.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

import java.util.List;

public class CarServiceAggregator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceAggregator.class);

    public Message<Car> aggregator(List<Message<Car>> tasks) {
        if (tasks != null && tasks.size() > 0) {
            Message<Car> service = tasks.get(0);

            LOGGER.info("Task # 0: {} (in Xml)", service.toString());

            for (int i = 1; i < tasks.size(); i++) {
                Message<Car> next = tasks.get(i);

                LOGGER.info("Task # {}: {} (in Xml)", i, next.toString());

                service.getPayload()
                        .getServices()
                        .putAll(next.getPayload().getServices());
            }

            LOGGER.info("Final service: {} (in Xml)", service.toString());

            return service;
        }
        return null;
    }
}
