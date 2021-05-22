/*
 * Name:   DbProcessHandler
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class DbProcessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbProcessHandler.class);

    private final static String ORDER_NO = "ORDER_NO";
    private final static String ITEM = "ITEM";

    public void handler(List<Map<String, Object>> list) {
        LOGGER.info("No of records to process: {}", list.size());

        for (Map<String, Object> rec : list) {
            LOGGER.info("Processed order {} for item {}", rec.get(ORDER_NO), rec.get(ITEM));
        }
    }
}
