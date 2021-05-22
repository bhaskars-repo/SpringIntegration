/*
 * Name:   SpotPricesGateway2
 * Author: Bhaskar S
 * Date:   05/08/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p4;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@MessagingGateway(name = "gateway", defaultRequestChannel = "inChannel", defaultReplyChannel = "outChannel")
public interface SpotPricesGateway2 {
    @Gateway(requestChannel = "inChannel", replyChannel = "outChannel",
            headers = { @GatewayHeader(name = "f", expression = "#args[0]"),
                    @GatewayHeader(name = "Content-Type", value = "application/json") })
    public String preciousMetalPrices(String type);
}
