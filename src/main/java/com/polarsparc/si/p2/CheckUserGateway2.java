/*
 * Name:   CheckUserGateway2
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@MessagingGateway(name = "gateway", defaultRequestChannel = "inChannel", errorChannel = "errChannel")
public interface CheckUserGateway2 {
    public boolean checkUser(String id);
}
