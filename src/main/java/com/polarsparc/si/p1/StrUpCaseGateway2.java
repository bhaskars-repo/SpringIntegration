/*
 * Name:   StrUpCaseGateway2
 * Author: Bhaskar S
 * Date:   04/16/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p1;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@MessagingGateway(name = "gateway", defaultRequestChannel = "inChannel", defaultReplyChannel = "outChannel")
public interface StrUpCaseGateway2 {
    public String receive();

    public void send(String msg);
}
