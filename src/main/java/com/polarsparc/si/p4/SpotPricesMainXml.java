/*
 * Name:   SpotPricesMainXml
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpotPricesMainXml {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpotPricesMainXml.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("p4/SpotPrices.xml");

        SpotPricesGateway gateway = (SpotPricesGateway) context.getBean("gateway");

        LOGGER.info("Precious Metal Prices with Xml = {}", gateway.preciousMetalPrices("json"));
    }
}
