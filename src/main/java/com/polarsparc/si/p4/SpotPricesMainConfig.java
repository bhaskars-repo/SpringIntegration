/*
 * Name:   SpotPricesMainConfig
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpotPricesMainConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpotPricesMainConfig.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpotPricesGateway2.class,
                SpotPricesConfig.class);

        SpotPricesGateway2 gateway = (SpotPricesGateway2) context.getBean("gateway");

        LOGGER.info("Precious Metal Prices with Config = {}", gateway.preciousMetalPrices("json"));
    }
}
