/*
 * Name:   CarServiceGateway2
 * Author: Bhaskar S
 * Date:   05/22/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p6;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@MessagingGateway(name = "gateway", defaultRequestChannel = "inChannel")
public interface CarServiceGateway2 {
    public void serviceCar(Car car);
}
