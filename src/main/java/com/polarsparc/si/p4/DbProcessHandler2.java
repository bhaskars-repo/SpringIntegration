/*
 * Name:   DbProcessHandler2
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

import java.util.List;
import java.util.Map;

@Configuration
@EnableIntegration
public class DbProcessHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbProcessHandler2.class);

    private final static String ORDER_NO = "ORDER_NO";
    private final static String ITEM = "ITEM";

    @ServiceActivator(inputChannel = "inChannel")
    public void handler(List<Map<String, Object>> list) {
        LOGGER.info("No of records to process: {}", list.size());

        for (Map<String, Object> rec : list) {
            LOGGER.info("Processed order {} for item {}", rec.get(ORDER_NO), rec.get(ITEM));
        }
    }
}
