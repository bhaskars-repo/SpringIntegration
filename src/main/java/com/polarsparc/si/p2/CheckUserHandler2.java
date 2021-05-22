/*
 * Name:   CheckUserHandler2
 * Author: Bhaskar S
 * Date:   04/24/2021
 * Blog:   https://www.polarsparc.com
 */

package com.polarsparc.si.p2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableIntegration
public class CheckUserHandler2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckUserHandler2.class);

    List<String> users = Arrays.asList("alice", "bob", "charlie");

    @ServiceActivator(inputChannel = "inChannel")
    public boolean checkUser(String id) {
        LOGGER.info("Check user = {}", id);

        if (!users.contains(id)) {
            throw new RuntimeException("User " + id + " is invalid");
        }

        return true;
    }
}
